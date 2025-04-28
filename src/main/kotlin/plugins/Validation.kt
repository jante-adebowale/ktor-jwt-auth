package com.janteadebowale.plugins

import com.janteadebowale.dto.request.LoginRequest
import com.janteadebowale.dto.request.RegistrationRequest
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

fun Application.configureValidation() {
    install(RequestValidation) {
        validate<LoginRequest> { payload ->
            when{
                payload.username.isBlank() -> ValidationResult.Invalid("Username can't be empty!")
                payload.password.isBlank() -> ValidationResult.Invalid("Password can't be empty!")
                payload.dialCode.isBlank() -> ValidationResult.Invalid("DialCode can't be empty")
                else -> ValidationResult.Valid
            }
        }

        validate<RegistrationRequest> { payload ->
            when{
                payload.name.isBlank() -> ValidationResult.Invalid("Name can't be empty!")
                payload.password.isBlank() -> ValidationResult.Invalid("Password can't be empty!")
                payload.phone.isBlank() -> ValidationResult.Invalid("Phone can't be empty!")
                payload.dialCode.isBlank() -> ValidationResult.Invalid("DialCode can't be empty")
                else -> ValidationResult.Valid
            }
        }
    }

}
