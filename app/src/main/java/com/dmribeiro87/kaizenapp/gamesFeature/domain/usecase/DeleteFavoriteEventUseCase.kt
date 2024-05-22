package com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase

import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import javax.inject.Inject

class DeleteFavoriteEventUseCase @Inject constructor(
    private val repository: SportsRepository
) {
    suspend operator fun invoke(eventId: String) {
        repository.deleteEvent(eventId)
    }
}