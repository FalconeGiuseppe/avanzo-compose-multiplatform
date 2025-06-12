package com.jdev.avanzo.domain

import com.jdev.avanzo.data.remote.model.Food
import com.jdev.avanzo.util.network.NetworkError
import com.jdev.avanzo.util.network.Result

interface FoodRepository {
    suspend fun getFoods(): Result<List<Food>, NetworkError>
}