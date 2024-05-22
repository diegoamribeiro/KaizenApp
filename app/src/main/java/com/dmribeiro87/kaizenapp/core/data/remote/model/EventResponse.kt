package com.dmribeiro87.kaizenapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("i") val id: String,
    @SerializedName("si") val sportId: String,
    @SerializedName("d") val description: String,
    @SerializedName("sh") val shortDescription: String,
    @SerializedName("tt") val startTime: Long
)