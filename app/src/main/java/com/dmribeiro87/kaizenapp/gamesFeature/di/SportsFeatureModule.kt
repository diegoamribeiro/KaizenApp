package com.dmribeiro87.kaizenapp.gamesFeature.di

import com.dmribeiro87.kaizenapp.core.data.remote.SportsApi
import com.dmribeiro87.kaizenapp.gamesFeature.data.datasource.SportsDataSource
import com.dmribeiro87.kaizenapp.gamesFeature.data.repository.SportsDataSourceImpl
import com.dmribeiro87.kaizenapp.gamesFeature.data.repository.SportsRepositoryImpl
import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.GetSportsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SportsFeatureModule {

    @Provides
    @Singleton
    fun provideSportsDataSource(api: SportsApi): SportsDataSource {
        return SportsDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideSportsRepository(dataSource: SportsDataSource): SportsRepository {
        return SportsRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetSportsUseCase(sportsRepository: SportsRepository): GetSportsUseCase {
        return GetSportsUseCase(sportsRepository)
    }
}