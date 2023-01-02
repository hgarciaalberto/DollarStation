package com.ahgitdevelopment.dollarstation.model.remote

import com.google.gson.annotations.SerializedName

data class HistoryRemote(
    @SerializedName("date")
    val date: String?,
    @SerializedName("value")
    val value: Float?
)
