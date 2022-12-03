package com.ahgitdevelopment.dollarstation.network

import com.ahgitdevelopment.dollarstation.model.remote.CurrencyRemote
import retrofit2.http.GET

interface DollarApi {

    @GET("getDollars/")
    suspend fun getDollars(): List<CurrencyRemote>

}