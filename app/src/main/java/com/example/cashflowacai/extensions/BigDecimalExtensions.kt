package com.example.cashflowacai.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.toBrazilianReal(): String{
    val formater: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formater.format(this)
}