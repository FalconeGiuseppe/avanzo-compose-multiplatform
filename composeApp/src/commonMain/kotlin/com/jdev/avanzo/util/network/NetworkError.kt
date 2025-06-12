package com.jdev.avanzo.util.network

enum class NetworkError : Error {
    UNKNOWN,
    CONFLICT,
    NO_INTERNET,
    UNAUTHORIZED,
    SERVER_ERROR,
    SERIALIZATION,
    REQUEST_TIMEOUT,
    PAYLOAD_TOO_LARGE,
    TOO_MANY_REQUESTS,
    DIFFERENT_BODY_TYPE,
}