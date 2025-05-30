package com.jdev.avanzo.domain

import com.jdev.avanzo.data.remote.model.FoodDetail
import com.jdev.avanzo.util.NetworkError
import com.jdev.avanzo.util.Result

interface FoodRepository {
    suspend fun addFood(): Result<FoodDetail, NetworkError>
}