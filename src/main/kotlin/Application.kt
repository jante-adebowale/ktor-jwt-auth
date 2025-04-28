package com.janteadebowale

import com.janteadebowale.database.DatabaseFactory
import com.janteadebowale.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureExceptionHandler()
    configureValidation()
    configureKoin()
    configureSecurity()
    configureRouting()
    configureMonitoring()
}
