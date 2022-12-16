package com.ahgitdevelopment.dollarstation.model.mapper

import com.ahgitdevelopment.dollarstation.extensions.getCurrencyName
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.model.remote.CurrencyRemote
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Mapper {
    fun CurrencyRemote.toDomain() = Currency(
        name = this.name.getCurrencyName(),
        buy = this.buy?.toFloat() ?: 0.0f,
        sell = this.sell?.toFloat() ?: 0.0f,
        date = this.date?.parseDate(),
        variation = this.variation?.replace("%", "")?.toFloat() ?: -1f
    )
}

private fun String?.parseDate(): Date? = this?.let { date ->
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(date)
}
