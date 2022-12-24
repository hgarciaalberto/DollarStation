package com.ahgitdevelopment.dollarstation.model.local

import java.time.LocalDateTime

data class Currency(
    val currencyType: CurrencyType,
    val buy: Float,
    val sell: Float,
    val date: LocalDateTime,
    val variation: Float
)
