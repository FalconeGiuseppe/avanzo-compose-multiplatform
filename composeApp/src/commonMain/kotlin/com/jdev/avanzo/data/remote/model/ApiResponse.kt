package com.jdev.avanzo.data.remote.model

class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T
)