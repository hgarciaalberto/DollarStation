package com.ahgitdevelopment.dollarstation.model.remote

import com.google.gson.annotations.SerializedName

data class CurrencyRemote(
    @SerializedName("buy")
    val buy: String?,
    @SerializedName("class-variation")
    val classVariation: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sell")
    val sell: String?,
    @SerializedName("variation")
    val variation: String?
)