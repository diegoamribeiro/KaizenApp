package com.dmribeiro87.kaizenapp.gamesFeature.data.repository

import com.dmribeiro87.kaizenapp.core.data.local.EventDao
import com.dmribeiro87.kaizenapp.core.data.local.entity.EventEntity
import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.data.mapper.toEntity
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports
import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import javax.inject.Inject


class SportsRepositoryImpl @Inject constructor(
    private val sportsDataSource: SportsDataSource,
    private val eventDao: EventDao
) : SportsRepository {

    override suspend fun getSportsEvents(): Resource<List<Sports>> {
        return sportsDataSource.getSportsEvents()
    }

    override suspend fun insertEvent(event: Event) {
        eventDao.insertEvent(event.toEntity())
    }

    override suspend fun deleteEvent(eventId: String) {
        eventDao.deleteEvent(eventId)
    }

    override suspend fun getFavoriteEvents(): List<EventEntity> {
        return eventDao.getFavoriteEvents()
    }
}



