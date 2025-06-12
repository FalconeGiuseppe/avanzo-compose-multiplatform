package com.jdev.avanzo.data.remote

import com.jdev.avanzo.data.remote.model.Food
import com.jdev.avanzo.util.network.NetworkError
import com.jdev.avanzo.util.network.Result

interface FoodService {
    suspend fun getFoods(): Result<List<Food>, NetworkError>
}