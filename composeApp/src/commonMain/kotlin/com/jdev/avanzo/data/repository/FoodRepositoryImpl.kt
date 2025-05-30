package com.jdev.avanzo.data.repository

import com.jdev.avanzo.data.remote.FoodService
import com.jdev.avanzo.data.remote.model.FoodDetail
import com.jdev.avanzo.domain.FoodRepository
import com.jdev.avanzo.util.NetworkError
import com.jdev.avanzo.util.Result

class FoodRepositoryImpl(
    private val apiService: FoodService
): FoodRepository {
    override suspend fun addFood(): Result<FoodDetail, NetworkError> =
        apiService.addFood()
}