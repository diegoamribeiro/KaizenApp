package com.dmribeiro87.kaizenapp.gamesFeature.data.repository

import com.dmribeiro87.kaizenapp.core.data.remote.SportsApi
import com.dmribeiro87.kaizenapp.core.data.remote.model.SportResponseItem
import com.dmribeiro87.kaizenapp.gamesFeature.data.datasource.SportsDataSource
import retrofit2.Response
import javax.inject.Inject

class SportsDataSourceImpl @Inject constructor(
    private val sportsApi: SportsApi
) : SportsDataSource {

    override suspend fun getSportsEvents(): Response<List<SportResponseItem>> {
        return sportsApi.getSportsEvents()
    }
}