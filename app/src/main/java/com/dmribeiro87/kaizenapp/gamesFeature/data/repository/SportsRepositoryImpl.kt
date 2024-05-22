package com.dmribeiro87.kaizenapp.gamesFeature.data.repository

import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.data.datasource.SportsDataSource
import com.dmribeiro87.kaizenapp.gamesFeature.data.mapper.toSport
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports
import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import javax.inject.Inject

class SportsRepositoryImpl @Inject constructor(
    private val sportsDataSource: SportsDataSource
) : SportsRepository {

    override suspend fun getSportsEvents(): Resource<List<Sports>> {
        return try {
            val response = sportsDataSource.getSportsEvents()
            if (response.isSuccessful) {
                response.body()?.let { sportsResponseItems ->
                    val sports =
                        sportsResponseItems.map { it.toSport() }
                    Resource.Success(sports)
                } ?: Resource.Error("An unknown error occurred")
            } else {
                Resource.Error("An unknown error occurred")
            }
        } catch (e: Exception) {
            Resource.Error("Couldn't reach the server. Check your internet connection")
        }
    }
}


