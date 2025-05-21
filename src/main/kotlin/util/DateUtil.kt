package com.janteadebowale.util

import kotlinx.datetime.*
import java.time.Instant
import java.time.ZoneOffset

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
object DateUtil {
    fun currentDateTime() = Clock.System.now().toLocalDateTime(TimeZone.UTC)

    fun getExpirationInstantInMinutes(minutes: Long): Instant {
        return java.time.LocalDateTime.now().plusMinutes(minutes).toInstant(
            ZoneOffset.UTC
        )
    }

    fun getExpirationInstantInSeconds(seconds: Long): Instant {
        return java.time.LocalDateTime.now().plusSeconds(seconds).toInstant(
            ZoneOffset.UTC
        )
    }

    fun getExpirationInstantInDays(days: Long): Instant {
        return java.time.LocalDateTime.now().plusDays(days).toInstant(
            ZoneOffset.UTC
        )
    }
}