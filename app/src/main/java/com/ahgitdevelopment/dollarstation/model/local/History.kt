package com.ahgitdevelopment.dollarstation.model.local

import java.time.LocalDateTime

data class History(
    val date: LocalDateTime,
    val value: Float
)
