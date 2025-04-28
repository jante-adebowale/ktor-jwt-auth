package com.janteadebowale.database

import com.janteadebowale.database.tables.UserTable
import com.janteadebowale.database.tables.UserTokenTable
import com.janteadebowale.model.ExposedToken
import com.janteadebowale.model.ExposedUser
import org.jetbrains.exposed.sql.ResultRow

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
object TableConverter {

    fun rowToExposedUser(row: ResultRow): ExposedUser {
        return ExposedUser(
            id = row[UserTable.id].toString(),
            name = row[UserTable.name],
            password = row[UserTable.password],
            phone = row[UserTable.phone],
        )
    }

    fun rowToExposedToken(row: ResultRow): ExposedToken {
        return ExposedToken(
            id = row[UserTokenTable.id],
            token = row[UserTokenTable.token],
            refreshToken = row[UserTokenTable.refreshToken],
            userId = row[UserTokenTable.userId],
            revoked = row[UserTokenTable.revoked]
        )
    }
}