package com.jdev.avanzo.presentation

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdev.avanzo.domain.usecase.FoodUseCase
import com.jdev.avanzo.presentation.model.FoodUiModel
import com.jdev.avanzo.util.network.onError
import com.jdev.avanzo.util.network.onSuccess
import com.jdev.avanzo.util.toByteArray
import com.jdev.avanzo.util.toImageBitmapFromBase64
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val foodUseCase: FoodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FoodUiState())
    val uiState: StateFlow<FoodUiState> = _uiState.asStateFlow()

    fun getFoods() {
        viewModelScope.launch {
            foodUseCase().collect { result ->
                _uiState.value = _uiState.value.copy(isLoading = true)
                result.onSuccess {
                    _uiState.value = _uiState.value.copy(foods = it.map { food ->
                        FoodUiModel(
                            id = food.id,
                            userId = food.userId,
                            title = food.title,
                            status = food.status,
                            isFree = food.isFree,
                            image = food.image.trim()
                                .substringAfter(",")
                                .replace("\n", "")
                                .replace("\r", "")
                                .toByteArray()
                                .toImageBitmapFromBase64()
                                ?: ImageBitmap(
                                    height = 1,
                                    width = 1
                                ),
                            pickupAddress = food.pickupAddress,
                            pickupTime = food.pickupTime,
                            description = food.description,
                        )
                    })
                }.onError { it ->
                    _uiState.value = _uiState.value.copy(isErrorState = true, errorMessage = it.name)
                }
            }
        }
    }
}