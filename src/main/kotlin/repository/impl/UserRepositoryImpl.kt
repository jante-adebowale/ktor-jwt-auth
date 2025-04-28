package com.janteadebowale.repository.impl

import com.janteadebowale.database.DatabaseFactory.query
import com.janteadebowale.database.TableConverter
import com.janteadebowale.database.tables.UserTable
import com.janteadebowale.model.ExposedUser
import com.janteadebowale.repository.UserRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class UserRepositoryImpl : UserRepository {
    override suspend fun addUser(exposedUser: ExposedUser) = query {
        UserTable.insert {
            it[id] = exposedUser.id
            it[name] = exposedUser.name
            it[phone] = exposedUser.phone
            it[password] = exposedUser.password
        }
    }.insertedCount > 0

    override suspend fun phoneExist(phone: String) = query {
        UserTable.selectAll().where(UserTable.phone eq (phone))
            .count() > 0
    }

    override suspend fun findUserByPhone(phone: String): ExposedUser? =
        query {
            UserTable.selectAll()
                .where(UserTable.phone eq phone)
                .map(TableConverter::rowToExposedUser)
                .singleOrNull()
        }

    override suspend fun findUserById(id: String): ExposedUser? = query {
        UserTable.selectAll()
            .where(UserTable.id eq id)
            .map(TableConverter::rowToExposedUser)
            .singleOrNull()
    }
}