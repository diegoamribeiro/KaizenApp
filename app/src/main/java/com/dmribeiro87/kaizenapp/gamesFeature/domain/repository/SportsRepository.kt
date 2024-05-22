package com.dmribeiro87.kaizenapp.gamesFeature.domain.repository

import com.dmribeiro87.kaizenapp.core.data.local.entity.EventEntity
import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports

interface SportsRepository {
    suspend fun getSportsEvents(): Resource<List<Sports>>
    suspend fun insertEvent(event: Event)
    suspend fun deleteEvent(eventId: String)
    suspend fun getFavoriteEvents(): List<EventEntity>
}