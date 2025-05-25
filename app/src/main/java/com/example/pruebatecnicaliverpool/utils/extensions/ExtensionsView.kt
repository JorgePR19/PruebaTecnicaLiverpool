package com.example.pruebatecnicaliverpool.utils.extensions

import com.example.pruebatecnicaliverpool.utils.Constants
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Locale

fun String.getMinSortPrice(): String{
    return when{
        this == Constants.TypeSort.LOWEST_PRICE.s -> "sortPrice|0"
        this == Constants.TypeSort.HIGHER_PRICE.s -> "sortPrice|1"
        else -> "Relevancia|0"
    }
}

fun Double.formatMoney(): String {
    val format = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"))
    format.minimumFractionDigits = 2
    format.roundingMode = RoundingMode.DOWN
    return format.format(this).trim()
}