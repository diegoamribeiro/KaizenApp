package com.dmribeiro87.kaizenapp.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.annotation.IntDef
import dagger.hilt.android.qualifiers.ApplicationContext

@IntDef(View.GONE, View.INVISIBLE)
annotation class HideTypeDef

fun formatTime(remainingTime: Long): String {
    val days = remainingTime / (24 * 3600)
    val hours = (remainingTime % (24 * 3600)) / 3600
    val minutes = (remainingTime % 3600) / 60
    val seconds = remainingTime % 60
    return String.format("%dd %dh %dmin %ds", days, hours, minutes, seconds)
}

fun String.capitalizeFirstLetter(): String {
    return this.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun View.show() = run { this.visibility = View.VISIBLE }
fun View.hide(@HideTypeDef hideType: Int = View.GONE) = run { this.visibility = hideType }