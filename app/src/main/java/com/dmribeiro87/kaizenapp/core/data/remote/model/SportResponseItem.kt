package com.dmribeiro87.kaizenapp.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class SportResponseItem(
    @SerializedName("i") val id: String,
    @SerializedName("d") val name: String,
    @SerializedName("e") val events: List<EventResponse>
)


