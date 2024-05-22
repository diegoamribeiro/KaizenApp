package com.dmribeiro87.kaizenapp.gamesFeature.di

import com.dmribeiro87.kaizenapp.core.data.local.EventDao
import com.dmribeiro87.kaizenapp.core.data.remote.SportsApi
import com.dmribeiro87.kaizenapp.gamesFeature.data.repository.SportsDataSource
import com.dmribeiro87.kaizenapp.gamesFeature.data.repository.SportsRepositoryImpl
import com.dmribeiro87.kaizenapp.gamesFeature.domain.repository.SportsRepository
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.DeleteFavoriteEventUseCase
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.GetFavoriteEventsUseCase
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.GetSportsUseCase
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.InsertFavoriteEventUseCase
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
        return SportsDataSource(api)
    }

    @Provides
    @Singleton
    fun provideSportsRepository(dataSource: SportsDataSource, eventDao: EventDao): SportsRepository {
        return SportsRepositoryImpl(dataSource, eventDao)
    }

    @Provides
    @Singleton
    fun provideGetSportsUseCase(sportsRepository: SportsRepository): GetSportsUseCase {
        return GetSportsUseCase(sportsRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteEventsUseCase(sportsRepository: SportsRepository): GetFavoriteEventsUseCase {
        return GetFavoriteEventsUseCase(sportsRepository)
    }

    @Provides
    @Singleton
    fun provideInsertFavoriteEventUseCase(sportsRepository: SportsRepository): InsertFavoriteEventUseCase {
        return InsertFavoriteEventUseCase(sportsRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteFavoriteEventUseCase(sportsRepository: SportsRepository): DeleteFavoriteEventUseCase {
        return DeleteFavoriteEventUseCase(sportsRepository)
    }
}
