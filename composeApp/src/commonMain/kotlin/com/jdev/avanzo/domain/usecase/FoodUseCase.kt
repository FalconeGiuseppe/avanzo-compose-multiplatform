package com.jdev.avanzo.domain.usecase

import com.jdev.avanzo.data.remote.model.Food
import com.jdev.avanzo.domain.FoodRepository
import com.jdev.avanzo.util.network.NetworkError
import com.jdev.avanzo.util.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodUseCase(
    private val foodRepository: FoodRepository
) {
    operator fun invoke(): Flow<Result<List<Food>, NetworkError>> =
        flow {
            foodRepository.getFoods()
        }
}