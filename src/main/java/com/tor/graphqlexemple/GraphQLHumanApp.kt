package com.tor.graphqlexemple

import com.tor.graphqlexemple.dao.importData
import com.tor.graphqlexemple.di.mainModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.locations.Locations
import org.koin.Koin
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext.startKoin

fun Application.main() {
    Koin.logger = PrintLogger()
    startKoin(listOf(mainModule))

    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    importData()
    log.info("Application setup complite")
}