package com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase

import com.dmribeiro87.kaizenapp.gamesFeature.data.mapper.toDomain
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event
import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import javax.inject.Inject

class GetFavoriteEventsUseCase @Inject constructor(
    private val sportsRepository: SportsRepository
) {
    suspend operator fun invoke(): List<Event> {
        return sportsRepository.getFavoriteEvents().map { it.toDomain() }
    }
}