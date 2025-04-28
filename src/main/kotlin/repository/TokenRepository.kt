package com.janteadebowale.repository

import com.janteadebowale.model.ExposedToken
import kotlinx.datetime.LocalDateTime

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
interface TokenRepository {
    suspend fun save(exposedToken: ExposedToken): Boolean

    suspend fun findByToken(accessToken: String): ExposedToken?

    suspend fun findByRefreshToken(refreshToken: String): ExposedToken?

    suspend fun findByUserId(userId: String): ExposedToken?

    suspend fun revokedAllTokens(userId: String, dateTime: LocalDateTime): Boolean

    suspend fun logout(userId: String, dateTime: LocalDateTime): Boolean
}