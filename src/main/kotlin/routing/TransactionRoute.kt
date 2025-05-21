package com.janteadebowale.routing

import com.janteadebowale.service.JwtService.Companion.getUsername
import com.janteadebowale.service.TransactionService
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/

fun Route.transactionRoute(transactionService: TransactionService){
    get("/recent") {
        val username = call.getUsername() ?: return@get call.respond(HttpStatusCode.BadRequest)
        val responsePayload =  transactionService.recentTransactions();
        call.respond(HttpStatusCode.OK,responsePayload)
    }
}