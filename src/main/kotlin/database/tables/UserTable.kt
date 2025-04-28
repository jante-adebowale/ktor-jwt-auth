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
object UserTable : Table("users") {
    val id = varchar("id", length = 100)
    val phone = varchar("phone",30).uniqueIndex()
    val name = varchar("name",100)
    val password = text("password")
    val createdAt = datetime("server_time").defaultExpression(CurrentDateTime)
    override val primaryKey = PrimaryKey(id, name = "users_pk")
}