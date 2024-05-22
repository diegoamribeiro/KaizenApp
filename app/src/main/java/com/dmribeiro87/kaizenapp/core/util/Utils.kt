package com.dmribeiro87.kaizenapp.core.util

fun formatTime(remainingTime: Long): String {
    val days = remainingTime / (24 * 3600)
    val hours = (remainingTime % (24 * 3600)) / 3600
    val minutes = (remainingTime % 3600) / 60
    val seconds = remainingTime % 60
    return String.format("%dd %dh %dmin %ds", days, hours, minutes, seconds)
}