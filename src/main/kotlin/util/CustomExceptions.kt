package com.janteadebowale.util

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

class UnauthorizedException(message: String = "Unauthorized") : RuntimeException(message)

class NotFoundException(message: String = "Not found") : RuntimeException(message)

class ConflictException(message: String = "Conflict") : RuntimeException(message)

class ServerErrorException(message: String = "Internal Server Error Occurred!") : RuntimeException(message)