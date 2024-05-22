package com.dmribeiro87.kaizenapp.gamesFeature.domain.model


data class Event(
    val id: String,
    val sportId: String,
    val competitorOne: String,
    val competitorTwo: String,
    val shortDescription: String,
    val startTime: Long,
    var isFavorite: Boolean
)

