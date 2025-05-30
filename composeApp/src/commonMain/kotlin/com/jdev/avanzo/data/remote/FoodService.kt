package com.jdev.avanzo.data.remote

import com.jdev.avanzo.data.remote.model.FoodDetail
import com.jdev.avanzo.util.NetworkError
import com.jdev.avanzo.util.Result

interface FoodService {
    suspend fun addFood(): Result<FoodDetail, NetworkError>
}