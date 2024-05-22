package com.dmribeiro87.kaizenapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: String,
    val sportId: String,
    val competitorOne: String,
    val competitorTwo: String,
    val shortDescription: String,
    val startTime: Long,
    val isFavorite: Boolean
)
