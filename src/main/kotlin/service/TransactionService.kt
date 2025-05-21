package com.janteadebowale.service

import com.janteadebowale.dto.response.RecentTransactionResponse
import com.janteadebowale.util.AppUtil
import com.janteadebowale.util.AppUtil.getRandomDateTimeInLastWeek
import com.janteadebowale.util.AppUtil.randomTransactionAmount
import com.janteadebowale.util.AppUtil.randomTransactionType
import com.janteadebowale.util.DateUtil
import kotlin.random.Random

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
class TransactionService {
    fun recentTransactions(): List<RecentTransactionResponse> {
        val recent = List(10) {
            RecentTransactionResponse(
                transactionType = randomTransactionType(),
                amount = randomTransactionAmount(),
                time = getRandomDateTimeInLastWeek(),
                status = Random.nextBoolean()
            )
        }
        return recent
    }
}