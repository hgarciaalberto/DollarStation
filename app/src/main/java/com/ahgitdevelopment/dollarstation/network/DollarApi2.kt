package com.ahgitdevelopment.dollarstation.network

import com.ahgitdevelopment.dollarstation.model.local.DbCurrency
import retrofit2.http.GET
import retrofit2.http.Path

interface DollarApi2 {

    @GET("currencies/")
    suspend fun getDollars(): List<DbCurrency>

    @GET("currencies/{currency}")
    suspend fun getDollars(
        @Path("currency") currency: String
    ): List<DbCurrency>

}
