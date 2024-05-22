package com.dmribeiro87.kaizenapp.gamesFeature.domain.model

data class Sports(
    val id: String,
    val name: String,
    val events: List<Event>
)
