package com.jdev.avanzo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdev.avanzo.domain.usecase.FoodUseCase
import com.jdev.avanzo.util.onError
import com.jdev.avanzo.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val foodUseCase: FoodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FoodUiState())
    val uiState: StateFlow<FoodUiState> = _uiState.asStateFlow()

    fun addFood() {
        viewModelScope.launch {
            foodUseCase().collect { result ->
                _uiState.value = _uiState.value.copy(isLoading = true)
                result.onSuccess {

                }.onError {
                    _uiState.value = _uiState.value.copy(isErrorState = true, errorMessage = it.name)
                }
            }
        }
    }
}