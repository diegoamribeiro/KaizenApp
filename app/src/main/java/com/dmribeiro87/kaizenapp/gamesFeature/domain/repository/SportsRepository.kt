package com.dmribeiro87.kaizenapp.gamesFeature.domain.repository

import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports

interface SportsRepository {
    suspend fun getSportsEvents(): Resource<List<Sports>>
}