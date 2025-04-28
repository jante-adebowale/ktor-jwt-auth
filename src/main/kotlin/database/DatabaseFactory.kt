package com.janteadebowale.database

import com.janteadebowale.database.tables.UserTable
import com.janteadebowale.database.tables.UserTokenTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : ktor-jwt-auth
 * YouTube   : https://www.youtube.com/@jante-adebowale
 * GitHub    : https://github.com/jante-adebowale
 ************************************************************/
object DatabaseFactory {
    private val DB_USER = System.getenv()["DB_USER"]
    private val DB_PASSWORD = System.getenv()["DB_PASSWORD"]
    private val DB_NAME = System.getenv()["DB_NAME"]
    private val DB_PORT = System.getenv()["DB_PORT"]
    private val DB_HOST = System.getenv()["DB_HOST"]

    fun init(inMemory: Boolean = true) {
        val database = if (inMemory) {
            Database.connect(
                url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                user = "root",
                driver = "org.h2.Driver",
                password = "",
            )
        } else {
            val connectionPool = hikariDatasource()
            Database.connect(connectionPool)
        }
        transaction(database) {
            SchemaUtils.create(UserTable, UserTokenTable)
        }
    }

    private fun hikariDatasource(): HikariDataSource {
        return HikariDataSource(HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://$DB_HOST:$DB_PORT/$DB_NAME"//jdbc:postgresql://localhost:5432/dbname
            password = "$DB_PASSWORD"
            username = "$DB_USER"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        });
    }

    suspend fun <T> query(block: () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}