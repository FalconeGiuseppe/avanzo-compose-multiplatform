package com.jdev.avanzo.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Food(
    val id: Int,
    val title: String,
    val description: String,
    @SerialName(value = "is_free")
    val isFree: Boolean,
    @SerialName(value = "pickup_time")
    val pickupTime: String,
    @SerialName(value = "pickup_address")
    val pickupAddress: String,
    @SerialName(value = "user_id")
    val userId: Int,
    val image: String,
    val status: String,
)

