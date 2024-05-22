package com.dmribeiro87.kaizenapp.gamesFeature.data.datasource

import com.dmribeiro87.kaizenapp.core.data.remote.model.SportResponseItem
import retrofit2.Response

interface SportsDataSource{
    suspend fun getSportsEvents(): Response<List<SportResponseItem>>
}