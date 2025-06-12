package com.jdev.avanzo.presentation.model

import androidx.compose.ui.graphics.ImageBitmap

data class FoodUiModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val status: String,
    val isFree: Boolean,
    val image: ImageBitmap,
    val pickupTime: String,
    val description: String,
    val pickupAddress: String,
)