package com.jdev.avanzo.presentation

data class FoodUiState(
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isErrorState: Boolean = false,
)