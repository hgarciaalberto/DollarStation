package com.ahgitdevelopment.dollarstation.repository

import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.model.mapper.Mapper.toDomain
import com.ahgitdevelopment.dollarstation.network.DollarApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val dollarApi: DollarApi
) {

    suspend fun getDollars(): Result<List<Currency>> {
        return try {
            Result.success(dollarApi.getDollars().map { it.toDomain() })
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}