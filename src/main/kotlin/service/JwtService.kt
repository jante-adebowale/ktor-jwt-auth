package com.janteadebowale.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.janteadebowale.repository.TokenRepository
import com.janteadebowale.repository.UserRepository
import com.janteadebowale.util.DateUtil
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class JwtService(
    private val applicationEnvironment: ApplicationEnvironment,
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {
    private val jwtAudience = getConfigProperty("jwt.audience")
    private val jwtIssuer = getConfigProperty("jwt.issuer")
    val jwtRealm = getConfigProperty("jwt.realm")
    private val jwtSecret = System.getenv()["SECRET_KEY"] ?: "secret"

    val logger = LoggerFactory.getLogger(JwtService::class.java)

    val jwtVerifier: JWTVerifier = JWT
        .require(Algorithm.HMAC256(jwtSecret))
        .withAudience(jwtAudience)
        .withIssuer(jwtIssuer)
        .build()

    fun createAccessToken(username: String) = createToken(
        username, expireAt = DateUtil.getExpirationInstantInSeconds(120)
    )

    fun createFreshToken(username: String) =
        createToken(
            username, expireAt = DateUtil.getExpirationInstantInDays(30)
        )

    private fun createToken(username: String, expireAt: Instant): String {
        val jwtToken = JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("username", username)
            .withSubject(username)
            .withExpiresAt(expireAt)
            .sign(Algorithm.HMAC256(jwtSecret))
        return jwtToken.toString()
    }

    suspend fun validate(authBearer: String?, credential: JWTCredential, isAccessToken: Boolean = true): JWTPrincipal? {
//        logger.info("JWT : $authBearer")
        if (authBearer == null) return null

        if (!authBearer.contains("Bearer ")) return null

        val jwt = if (authBearer.split(" ").size == 2) authBearer.split(" ")[1] else return null


        val findByToken =
            (if (isAccessToken) tokenRepository.findByToken(jwt) else tokenRepository.findByRefreshToken(jwt)) ?: return null

        if (findByToken.revoked) {
            tokenRepository.revokedAllTokens(findByToken.userId, DateUtil.currentDateTime())
            return null
        }

        if (isTokenExpired(credential)) return null

        val username = extractClaim(credential)

        return username?.let {
            val user = userRepository.findUserByPhone(it)
            if (user != null && validAudience(credential)) {
                JWTPrincipal(credential.payload)
            } else null
        }

    }

    private fun extractClaim(credential: JWTCredential, key: String = "username"): String? {
        return credential.payload.getClaim(key).asString()
    }

    private fun validAudience(credential: JWTCredential): Boolean =
        credential.payload.audience.contains(jwtAudience)


    private fun isTokenExpired(credential: JWTCredential): Boolean =
        credential.payload.expiresAtAsInstant.isBefore(LocalDateTime.now().toInstant(ZoneOffset.UTC))


    private fun getConfigProperty(path: String): String =
        applicationEnvironment.config.propertyOrNull(path)?.getString() ?: ""

    companion object {
        fun RoutingCall.getUsername(): String? {
            return this.principal<JWTPrincipal>()?.payload?.subject
        }
    }
}