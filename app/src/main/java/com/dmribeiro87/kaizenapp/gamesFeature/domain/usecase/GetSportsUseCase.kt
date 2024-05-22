package com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase

import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports
import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import javax.inject.Inject

class GetSportsUseCase @Inject constructor(
    private val sportsRepository: SportsRepository
) {
    suspend operator fun invoke(): Resource<List<Sports>> {
        return sportsRepository.getSportsEvents()
    }
}