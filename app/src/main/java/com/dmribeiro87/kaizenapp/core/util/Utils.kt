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

fun hasInternetConnection(@ApplicationContext appContext: Context): Boolean {

    val connectivityManager: ConnectivityManager = appContext.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> return false
    }
}

fun View.show() = run { this.visibility = View.VISIBLE }
fun View.hide(@HideTypeDef hideType: Int = View.GONE) = run { this.visibility = hideType }