package com.jdev.avanzo.presentation

import com.jdev.avanzo.presentation.model.FoodUiModel

data class FoodUiState(
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isErrorState: Boolean = false,
    val foods: List<FoodUiModel> = listOf()
)