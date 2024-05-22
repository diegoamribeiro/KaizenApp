package com.dmribeiro87.kaizenapp.gamesFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event

class FakeSportsViewModel : ViewModel() {
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    private val allEvents = listOf(
        Event("1", "Football", "Team A", "Team B", "Match A vs B", 1000, isFavorite = false),
        Event("2", "Football", "Team C", "Team D", "Match C vs D", 2000, isFavorite = true),
        Event("3", "Football", "Team E", "Team F", "Match E vs F", 3000, isFavorite = false),
        Event("4", "Football", "Team G", "Team H", "Match G vs H", 4000, isFavorite = true)
    )

    fun filterFavorites(showFavorites: Boolean) {
        if (showFavorites) {
            _events.value = allEvents.filter { it.isFavorite }
        } else {
            _events.value = allEvents
        }
    }
}