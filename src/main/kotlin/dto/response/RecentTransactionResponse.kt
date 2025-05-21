package com.janteadebowale.dto.response

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
@Serializable
data class RecentTransactionResponse(
    val transactionType:String,
    val amount:Double,
    val time: LocalDateTime,
    val status: Boolean  //True | False
)
