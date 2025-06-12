package com.jdev.avanzo.di.module

import com.jdev.avanzo.util.network.NetworkConstants
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val kotlinLogger = KotlinLogging.logger { }

val networkModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                Json {
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                        prettyPrint = true
                    }
                }

            }

            // Response Log
            install(ResponseObserver) {
                onResponse { response ->
                    kotlinLogger.info { "CLIENT-LOG-RESP: $response" }
                }
            }

            // Request log
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        kotlinLogger.info { "KTOR-LOG-REQ: $message" }
                    }
                }
                level = LogLevel.ALL
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = NetworkConstants.BASE_URL
                }
                contentType(ContentType.Application.Json)
            }
            headers {
                append("Accept", "application/json")
            }
        }
    }
}