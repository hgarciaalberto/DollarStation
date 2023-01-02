package com.ahgitdevelopment.dollarstation.model.local

enum class CurrencyType(val key: String, val fullName: String) {
    TURISTA("dolar_turista", "Dólar Turista"),
    INFORMAL("dolar_informal", "Dólar Blue"),
    CONTADO_LIQUIDACION("dolar_contado_liquidacion", "Dólar contado con Liquidación"),
    QATAR("dolar_qatar", "Dólar Qatar"),
    LUJO("dolar_lujo", "Dólar Lujo"),
    COLDPLAY("dolar_coldplay", "Dólar Coldplay"),
    MEP("dolar_mep", "Dólar MEP"),
    OFICIAL("dolar_oficial", "Dólar Oficial"),
    FUTURO("dolar_futuro", "Dólar Futuro"),
    MAYORISTA("dolar_mayorista", "Dólar Mayorista"),
    AHORRO("dolar_ahorro", "Dólar Ahorro"),
    NACION("dolar_nacion", "Dólar Banco Nación");

    companion object {
        fun getType(name: String?): CurrencyType = when (name) {
            "dolar_turista" -> TURISTA
            "dolar_informal" -> INFORMAL
            "dolar_contado_liquidacion" -> CONTADO_LIQUIDACION
            "dolar_qatar" -> QATAR
            "dolar_lujo" -> LUJO
            "dolar_coldplay" -> COLDPLAY
            "dolar_mep" -> MEP
            "dolar_oficial" -> OFICIAL
            "dolar_futuro" -> FUTURO
            "dolar_mayorista" -> MAYORISTA
            "dolar_ahorro" -> AHORRO
            "dolar_nacion" -> NACION
            else -> throw NoSuchElementException("Currency type not expected: $name")
        }
    }
}
