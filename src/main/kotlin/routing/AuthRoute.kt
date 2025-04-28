package com.janteadebowale.routing

import com.janteadebowale.dto.request.LoginRequest
import com.janteadebowale.dto.request.RegistrationRequest
import com.janteadebowale.service.AuthService
import com.janteadebowale.service.JwtService.Companion.getUsername
import io.ktor.http.*
import io.ktor.server.request.*
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


fun Route.register(authService: AuthService) {
    post("/register") {
        val request = call.receive<RegistrationRequest>()
        val tokenResponse = authService.registerUser(request)
        call.respond(HttpStatusCode.Created, tokenResponse)
    }
}

fun Route.login(authService: AuthService) {
    post("/login") {
        val loginRequest = call.receive<LoginRequest>()
        val loginResponse = authService.login(loginRequest)
        call.respond(HttpStatusCode.OK, loginResponse)
    }
}

fun Route.authMe(authService: AuthService) {
    get("/me") {
        val username = call.getUsername() ?: return@get call.respond(HttpStatusCode.BadRequest)
        val authMe = authService.authMe(username.trim())
        call.respond(HttpStatusCode.OK, authMe)
    }
}

fun Route.refreshToken(authService: AuthService) {
    get("/refresh-token") {
        val username = call.getUsername() ?: return@get call.respond(HttpStatusCode.BadRequest)
        val authMe = authService.refreshToken(username.trim())
        call.respond(HttpStatusCode.OK, authMe)
    }
}