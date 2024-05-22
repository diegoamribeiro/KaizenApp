package com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase

import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event
import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import javax.inject.Inject

class InsertFavoriteEventUseCase @Inject constructor(
    private val repository: SportsRepository
) {
    suspend operator fun invoke(event: Event) {
        repository.insertEvent(event)
    }
}