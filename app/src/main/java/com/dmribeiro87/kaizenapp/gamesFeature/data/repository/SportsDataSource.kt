package com.dmribeiro87.kaizenapp.gamesFeature.data.repository

import com.dmribeiro87.kaizenapp.core.data.remote.SportsApi
import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.data.mapper.toSport
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports
import javax.inject.Inject

class SportsDataSource @Inject constructor(
    private val sportsApiService: SportsApi
) {
    suspend fun getSportsEvents(): Resource<List<Sports>> {
        return try {
            val response = sportsApiService.getSportsEvents()
            if (response.isSuccessful) {
                response.body()?.let { sportsResponseItems ->
                    val sports = sportsResponseItems.map { it.toSport() }
                    Resource.Success(sports)
                } ?: Resource.Success(emptyList())
            } else {
                Resource.Error("An unknown error occurred")
            }
        } catch (e: Exception) {
            Resource.Error("Couldn't reach the server. Check your internet connection")
        }
    }
}