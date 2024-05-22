package com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase

import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import javax.inject.Inject

class GetSportsUseCase @Inject constructor(
    private val sportsRepository: SportsRepository
){
    suspend operator fun invoke() = sportsRepository.getSportsEvents()
}