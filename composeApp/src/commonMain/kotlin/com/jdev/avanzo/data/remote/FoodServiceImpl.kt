package com.jdev.avanzo.data.remote

import com.jdev.avanzo.data.remote.model.ApiResponse
import com.jdev.avanzo.data.remote.model.Food
import com.jdev.avanzo.util.network.NetworkError
import com.jdev.avanzo.util.network.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class FoodServiceImpl(
    val httpClient: HttpClient
) : FoodService {
    override suspend fun getFoods(): Result<List<Food>, NetworkError> {
        val response = try {
            httpClient.get(urlString = "foods") {}
        } catch (_: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (_: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {

                try {
                    val result = response.body<ApiResponse<List<Food>>>()
                    if (result.success) {
                        Result.Success(data = result.data)
                    } else {
                        Result.Error(NetworkError.UNKNOWN)
                    }

                    Result.Success(result.data)
                } catch (_: NoTransformationFoundException) {
                    Result.Error(NetworkError.DIFFERENT_BODY_TYPE)
                }
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            409 -> Result.Error(NetworkError.CONFLICT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)

            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)

            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}