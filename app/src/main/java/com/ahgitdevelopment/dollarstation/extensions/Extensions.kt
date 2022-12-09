package com.ahgitdevelopment.dollarstation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.ahgitdevelopment.dollarstation.R
import java.text.SimpleDateFormat
import java.util.Locale

fun Float.twoDecimals(): String = String.format("%.2f", this)

@Composable
fun Float.getRedGreenColor(): Color =
    if (this > 0)
        colorResource(R.color.green)
    else
        colorResource(R.color.red)

fun String.parseDateFormat(): String {
//    18/11/2022 - 15:49
//    18-11-2022 00:00
//    23-11-2022 - 17:56
//    Tue Jul 12 15:42:00 GMT+01:00 2022

    val parser = when (this.count { it == '-' }) {
        1 -> SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
        2 -> SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        3 -> SimpleDateFormat("dd-MM-yyyy - HH:mm", Locale.getDefault())
        else -> SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
//        else -> SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
    }

    val formatter = SimpleDateFormat("dd/MM/yyyy\nHH:mm", Locale.getDefault())
    return formatter.format(parser.parse(this)!!)
}

