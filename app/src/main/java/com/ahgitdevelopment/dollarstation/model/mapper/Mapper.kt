package com.ahgitdevelopment.dollarstation.model.mapper

import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.model.local.CurrencyType
import com.ahgitdevelopment.dollarstation.model.local.History
import com.ahgitdevelopment.dollarstation.model.remote.CurrencyRemote
import com.ahgitdevelopment.dollarstation.model.remote.HistoryRemote
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Mapper {

    fun CurrencyRemote.toDomain() = Currency(
        currencyType = CurrencyType.getType(this.name),
        buy = this.buy?.toFloat() ?: 0.0f,
        sell = this.sell?.toFloat() ?: 0.0f,
        date = LocalDateTime.parse(
            this.date,
            DateTimeFormatter.ofPattern(datePattern, Locale.getDefault())
        ),
        variation = this.variation?.replace("%", "")?.toFloat() ?: -1f
    )

    fun HistoryRemote.toDomain() = History(
        date = LocalDateTime.parse(date),
        value = value ?: 0f
    )
}

val datePattern = "yyyy-MM-dd HH:mm:ss"