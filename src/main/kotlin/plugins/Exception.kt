package com.janteadebowale.plugins

import com.janteadebowale.util.ConflictException
import com.janteadebowale.util.NotFoundException
import com.janteadebowale.util.ServerErrorException
import com.janteadebowale.util.UnauthorizedException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
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

fun Application.configureExceptionHandler() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is RequestValidationException -> {
                    call.respondText(cause.reasons.joinToString(","), status = HttpStatusCode.BadRequest)
                }

                is UnauthorizedException -> {
                    call.respondText(text = "${cause.message}", status = HttpStatusCode.Unauthorized)
                }

                is ConflictException -> {
                    call.respondText(text = "${cause.message}", status = HttpStatusCode.Conflict)
                }

                is NotFoundException -> {
                    call.respondText(text = "${cause.message}", status = HttpStatusCode.NotFound)
                }

                is ServerErrorException -> {
                    call.respondText(text = "${cause.message}", status = HttpStatusCode.InternalServerError)
                }

                else -> {
                    call.respondText(text = "${cause.message}", status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}
