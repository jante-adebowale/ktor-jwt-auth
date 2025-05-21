package com.janteadebowale.service

import com.janteadebowale.dto.request.LoginRequest
import com.janteadebowale.dto.request.RegistrationRequest
import com.janteadebowale.dto.response.AboutResponse
import com.janteadebowale.dto.response.TokenResponse
import com.janteadebowale.model.ExposedToken
import com.janteadebowale.model.ExposedUser
import com.janteadebowale.repository.TokenRepository
import com.janteadebowale.repository.UserRepository
import com.janteadebowale.util.*
import com.janteadebowale.util.AppUtil.generateUUID
import com.janteadebowale.util.AppUtil.generateUniqueDigits
import io.ktor.server.sessions.*

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class AuthService(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val jwtService: JwtService,
) {

    suspend fun registerUser(registrationRequest: RegistrationRequest): TokenResponse {
            val username = AppUtil.fineTunePhone(registrationRequest.phone, registrationRequest.dialCode)
            if (userRepository.phoneExist(username)) {
                throw ConflictException("Phone number is in-use")
            }

            val userId = generateUUID()
            userRepository.addUser(
                ExposedUser(
                    id = userId,
                    name = registrationRequest.name,
                    phone = username,
                    password = registrationRequest.password.toHashString()
                )
            )

            val accessTokenValue = jwtService.createAccessToken(username)
            val refreshTokenValue = jwtService.createFreshToken(username)
            tokenRepository.revokedAllTokens(userId, DateUtil.currentDateTime())
            tokenRepository.save(
                ExposedToken(
                    token = accessTokenValue,
                    refreshToken = refreshTokenValue,
                    userId = userId
                )
            )

            return TokenResponse(
                accessToken = accessTokenValue,
                refreshToken = refreshTokenValue,
            )

    }

    suspend fun login(loginRequest: LoginRequest): TokenResponse {
        val phone = AppUtil.fineTunePhone(loginRequest.username, loginRequest.dialCode)
        val user = userRepository.findUserByPhone(phone)
        if (user == null || !passwordMatches(loginRequest.password, user.password)) {
            throw UnauthorizedException("Invalid Phone or password!")
        }

        val accessTokenValue = jwtService.createAccessToken(phone)
        val refreshTokenValue = jwtService.createFreshToken(phone)
        tokenRepository.revokedAllTokens(user.id, DateUtil.currentDateTime())
        tokenRepository.save(
            ExposedToken(
                token = accessTokenValue,
                refreshToken = refreshTokenValue,
                userId = user.id
            )
        )

        return TokenResponse(
            accessToken = accessTokenValue,
            refreshToken = refreshTokenValue,
        )
    }

    suspend fun authMe(username: String): AboutResponse {
        val user = userRepository.findUserByPhone(username)
        return user?.let { exposedUser ->
            AboutResponse(
                name = exposedUser.name,
                phone = exposedUser.phone,
                sessionId = generateUniqueDigits()
            )

        } ?: throw NotFoundException()
    }

    suspend fun refreshToken(username: String): TokenResponse {
        return userRepository.findUserByPhone(username)?.let {
            tokenRepository.revokedAllTokens(it.id, DateUtil.currentDateTime())
            val accessToken = jwtService.createAccessToken(username)
            val refreshToken = jwtService.createFreshToken(username)
            tokenRepository.save(
                ExposedToken(
                    token = accessToken,
                    refreshToken = refreshToken,
                    userId = it.id,
                    revoked = false
                )
            )
            TokenResponse(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )

        } ?: throw NotFoundException()
    }
}