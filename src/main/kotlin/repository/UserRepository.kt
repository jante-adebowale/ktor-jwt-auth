package com.janteadebowale.repository

import com.janteadebowale.model.ExposedUser

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
interface UserRepository {
    suspend fun addUser(exposedUser: ExposedUser): Boolean

    suspend fun phoneExist(phone: String): Boolean

    suspend fun findUserByPhone(phone: String): ExposedUser?

    suspend fun findUserById(id: String): ExposedUser?
}