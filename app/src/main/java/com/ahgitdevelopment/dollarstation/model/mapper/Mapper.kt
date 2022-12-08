package com.ahgitdevelopment.dollarstation.model.mapper

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

fun String?.getCurrencyName(): String = when (this) {
    "dolar_turista" -> "Dólar Turista"
    "dolar_informal" -> "Dólar Blue"
    "dolar_contado_liquidacion" -> "Dólar contado con Liquidación"
    "dolar_qatar" -> "Dólar Qatar"
    "dolar_lujo" -> "Dólar Lujo"
    "dolar_coldplay" -> "Dólar Coldplay"
    "dolar_mep" -> "Dólar MEP"
    "dolar_oficial" -> "Dólar Oficial"
    "dolar_futuro" -> "Dólar Futuro"
    "dolar_mayorista" -> "Dólar Mayorista"
    "dolar_ahorro" -> "Dólar Ahorro"
    "dolar_nacion" -> "Dólar Banco Nación"
    else -> this ?: "nuevo dólar"
}

private fun String?.parseDate(): Date? = this?.let { date ->
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(date)
}
