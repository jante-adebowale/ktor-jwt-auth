package com.janteadebowale.util

import at.favre.lib.crypto.bcrypt.BCrypt

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

fun passwordMatches(plainPassword: String, hashedPassword: String): Boolean {
 return BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword).verified
}

fun String.toHashString(): String {
 return BCrypt.withDefaults().hashToString(12, this.toCharArray())
}