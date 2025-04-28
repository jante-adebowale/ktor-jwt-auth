package com.janteadebowale.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
object UserTokenTable : Table("user_token") {
    val id = integer("id").autoIncrement()
    val token = text("token")
    val refreshToken = text("refresh_token")
    val userId = varchar("user_id", 50)
    val revoked = bool("revoked").default(false)
    val revokedAt = datetime("revoked_at").nullable()
    val logout = bool("logout").default(false)
    val logoutAt = datetime("logout_at").nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    override val primaryKey = PrimaryKey(id, name = "user_token_pk")
}