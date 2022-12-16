package com.ahgitdevelopment.dollarstation.repository

import com.ahgitdevelopment.dollarstation.features.calculator.CalculatorViewModel
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.model.local.DbCurrency
import com.ahgitdevelopment.dollarstation.model.mapper.Mapper.toDomain
import com.ahgitdevelopment.dollarstation.network.DollarApi
import com.ahgitdevelopment.dollarstation.network.DollarApi2
import javax.inject.Inject

class Repository @Inject constructor(
    private val dollarApi: DollarApi,
    private val dollarApi2: DollarApi2
) {

    ///////////////////////////////////////////////////////////////////////////
    // API 1
    ///////////////////////////////////////////////////////////////////////////

    suspend fun getDollars(): Result<List<Currency>> {
        return try {
            Result.success(dollarApi.getDollars().map { it.toDomain() })
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    suspend fun calculator(
        exchangeType: CalculatorViewModel.ExchangeType,
        currency: String,
        value: String
    ): Result<String> {
        return try {
            when (exchangeType) {
                CalculatorViewModel.ExchangeType.BUY -> "compra"
                CalculatorViewModel.ExchangeType.SELL -> "venta"
            }.let { type ->
                Result.success(dollarApi.calculator(type, currency, value))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // API 2
    ///////////////////////////////////////////////////////////////////////////

    suspend fun getDollarsApi2(): Result<List<DbCurrency>> {
        return try {
            Result.success(dollarApi2.getDollars())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    suspend fun getCurrencyListApi2(currency: String): Result<List<DbCurrency>> {
        return try {
            Result.success(dollarApi2.getDollars(currency))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
