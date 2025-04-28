package com.janteadebowale.repository.impl

import com.janteadebowale.database.DatabaseFactory.query
import com.janteadebowale.database.TableConverter
import com.janteadebowale.database.tables.UserTokenTable
import com.janteadebowale.model.ExposedToken
import com.janteadebowale.repository.TokenRepository
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class TokenRepositoryImpl : TokenRepository {
    override suspend fun save(exposedToken: ExposedToken) = query {
        val result = UserTokenTable.insert {
            it[token] = exposedToken.token
            it[refreshToken] = exposedToken.refreshToken
            it[userId] = exposedToken.userId
        }
        result.insertedCount > 0
    }


    override suspend fun findByToken(accessToken: String): ExposedToken? = query {
        UserTokenTable.selectAll()
            .where(UserTokenTable.token eq accessToken)
            .map(TableConverter::rowToExposedToken).singleOrNull()
    }


    override suspend fun findByRefreshToken(refreshToken: String): ExposedToken? = query {
        UserTokenTable.selectAll()
            .where(UserTokenTable.refreshToken eq refreshToken)
            .map(TableConverter::rowToExposedToken).singleOrNull()
    }


    override suspend fun findByUserId(userId: String) = query {
        UserTokenTable.selectAll()
            .where(UserTokenTable.userId eq userId).singleOrNull()?.let { TableConverter.rowToExposedToken(it) }
    }


    override suspend fun revokedAllTokens(userId: String, dateTime: LocalDateTime) = query {
        UserTokenTable.update({ UserTokenTable.userId eq userId }) {
            it[revoked] = true
            it[revokedAt] = dateTime
        } > 0
    }


    override suspend fun logout(userId: String, dateTime: LocalDateTime) =
        query {
            UserTokenTable.update({ UserTokenTable.userId eq userId }) {
                it[revokedAt] = dateTime
                it[logoutAt] = dateTime
                it[revoked] = true
                it[logout] = true
            } > 0
        }
}