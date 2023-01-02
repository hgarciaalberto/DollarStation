package com.ahgitdevelopment.dollarstation.network

import com.ahgitdevelopment.dollarstation.model.remote.CurrencyRemote
import com.ahgitdevelopment.dollarstation.model.remote.HistoryRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface DollarApi {

    @GET("getDollars/")
    suspend fun getDollars(): List<CurrencyRemote>

    @GET("calculadora/{type}/{currency}/{value}")
    suspend fun calculator(
        @Path("type") type: String,
        @Path("currency") currency: String,
        @Path("value") value: String
    ): String

    @GET("historicoAll/{dolarname}")
    suspend fun getHistory(
        @Path("dolarname") currency: String
    ): List<HistoryRemote>
}