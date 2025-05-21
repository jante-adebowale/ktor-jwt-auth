package com.janteadebowale.plugins

import com.janteadebowale.routing.*
import com.janteadebowale.service.AuthService
import com.janteadebowale.service.TransactionService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val authService by inject<AuthService>()
    val transactionService by inject<TransactionService>()
    routing {
        route("/api") {
            route("/auth") {
                register(authService)

                login(authService)

                authenticate("access-token-auth") {
                    authMe(authService = authService)
                }

                authenticate("refresh-token-auth") {
                    refreshToken(authService = authService)
                }
            }

            route("/transactions") {
                authenticate("access-token-auth") {
                    transactionRoute(transactionService)
                }
            }
        }
    }
}
