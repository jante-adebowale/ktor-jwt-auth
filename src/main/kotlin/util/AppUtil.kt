package com.janteadebowale.util

import java.util.*

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
object AppUtil {

    fun generateUUID() = UUID.randomUUID().toString()

    fun generateUniqueDigits(length:Int = 4) = (0..9).shuffled().take(length).joinToString("")

    fun fineTunePhone(phone: String, dialCode: String) =
        if (phone.length == 10) "$dialCode$phone" else "$dialCode${phone.substring(1, phone.length)}"

}