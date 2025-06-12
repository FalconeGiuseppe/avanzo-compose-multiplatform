package com.jdev.avanzo.data.repository

import com.jdev.avanzo.data.remote.FoodService
import com.jdev.avanzo.data.remote.model.Food
import com.jdev.avanzo.domain.FoodRepository
import com.jdev.avanzo.util.network.NetworkError
import com.jdev.avanzo.util.network.Result

class FoodRepositoryImpl(
    private val apiService: FoodService
): FoodRepository {
    override suspend fun getFoods(): Result<List<Food>, NetworkError> =
        apiService.getFoods()
}