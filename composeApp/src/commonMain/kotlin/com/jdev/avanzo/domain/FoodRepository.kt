package com.jdev.avanzo.domain

import com.jdev.avanzo.data.remote.model.Food
import com.jdev.avanzo.util.NetworkError
import com.jdev.avanzo.util.Result

interface FoodRepository {
    suspend fun getFoods(): Result<Food, NetworkError>
}