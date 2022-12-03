package com.ahgitdevelopment.dollarstation.model.local

import java.util.Date

data class Currency(
    val name: String,
    val buy: Float,
    val sell: Float,
    val date: Date?,
    val variation: Float
)
