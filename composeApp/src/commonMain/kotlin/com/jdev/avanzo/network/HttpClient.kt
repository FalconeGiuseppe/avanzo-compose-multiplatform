package com.jdev.avanzo.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
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
                print("CLIENT-LOG-RESP: $response")
            }
        }

        // Request log
        install(Logging) {
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    print("KTOR-LOG-REQ: $message")
                }
            }
            level = LogLevel.BODY
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                // host = NetworkConstants.BASE_URL
            }
            contentType(ContentType.Application.Json)
        }
        headers {
            append("Accept", "application/json")
        }
    }
}