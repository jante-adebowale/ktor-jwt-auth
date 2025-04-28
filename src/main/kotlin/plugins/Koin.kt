package com.janteadebowale.plugins

import com.janteadebowale.repository.TokenRepository
import com.janteadebowale.repository.UserRepository
import com.janteadebowale.repository.impl.TokenRepositoryImpl
import com.janteadebowale.repository.impl.UserRepositoryImpl
import com.janteadebowale.service.AuthService
import com.janteadebowale.service.JwtService
import io.ktor.server.application.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger


val repositoryModule = module {
    singleOf(::UserRepositoryImpl).bind<UserRepository>()
    singleOf(::TokenRepositoryImpl).bind<TokenRepository>()
}

val serviceModule = module {
    singleOf(::JwtService)
    singleOf(::AuthService)
}

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(
            repositoryModule, serviceModule,
            module {
                single<ApplicationEnvironment> {
                    environment
                }
            }
        )
    }
}
