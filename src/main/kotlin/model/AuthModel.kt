package com.janteadebowale.model

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

data class ExposedToken(
    val id: Int? = 0,
    val token: String,
    val refreshToken: String,
    val userId: String,
    val revoked: Boolean = false
)

data class ExposedUser(
    val id: String,
    val name: String,
    val password: String,
    val phone: String,
)
