package com.dmribeiro87.kaizenapp.gamesFeature.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.DeleteFavoriteEventUseCase
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.GetFavoriteEventsUseCase
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.GetSportsUseCase
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.InsertFavoriteEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getSportsUseCase: GetSportsUseCase,
    private val getFavoriteEventsUseCase: GetFavoriteEventsUseCase,
    private val insertFavoriteEventUseCase: InsertFavoriteEventUseCase,
    private val deleteFavoriteEventUseCase: DeleteFavoriteEventUseCase
) : ViewModel() {

    private val _sportsData = MutableLiveData<Resource<List<Sports>>>()
    val sportsData: LiveData<Resource<List<Sports>>> = _sportsData

    fun getSportsEvents() {
        viewModelScope.launch {
            _sportsData.value = Resource.Loading()
            val sportsResource = getSportsUseCase()
            if (sportsResource is Resource.Success) {
                val favoriteEvents = getFavoriteEventsUseCase()
                val sportsWithFavorites = sportsResource.data?.map { sport ->
                    sport.copy(events = sport.events.map { event ->
                        event.copy(isFavorite = favoriteEvents.any { it.id == event.id })
                    })
                }
                _sportsData.value = Resource.Success(sportsWithFavorites)
            } else {
                _sportsData.value = sportsResource
            }
        }
    }

    fun updateFavorite(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            if (event.isFavorite) {
                insertFavoriteEventUseCase(event)
            } else {
                deleteFavoriteEventUseCase(event.id)
            }
        }
    }
}