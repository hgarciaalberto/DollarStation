package com.ahgitdevelopment.dollarstation.model.local

import com.google.firebase.firestore.PropertyName

class DbCurrency() {

    @PropertyName("currency")
    var currency: String = ""

    @PropertyName("compra")
    var compra = ""

    @PropertyName("venta")
    var venta = ""

    @PropertyName("fecha")
    var fecha = ""

    @PropertyName("variacion")
    var variacion = ""

    constructor(
        currency: String,
        compra: String,
        venta: String,
        fecha: String,
        variacion: String
    ) : this() {
        this.currency = currency
        this.compra = compra
        this.venta = venta
        this.fecha = fecha
        this.variacion = variacion
    }
//    constructor(
//        currency: String,
//        compra: String,
//        venta: String,
//        fecha: String,
//        variacion: String
//    ) : this(
//        currency = currency,
//        compra = compra,
//        venta = venta,
//        fecha = fecha,
//        variacion = variacion
//    ) {
//        this.currency = currency,
//        this.compra = compra
//    }
}

