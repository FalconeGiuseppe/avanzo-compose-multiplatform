package com.jdev.avanzo.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodDetail(
    val title: String,
    val description: String,
    val pickupAddress: String,
    val userId: Int,
    val isFree: Boolean,
    val pickupTime: String,
    val image: String,
)
