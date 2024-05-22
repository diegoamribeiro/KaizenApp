package com.dmribeiro87.kaizenapp.gamesFeature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SportsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `filterFavorites should return only favorite events when true`() {
        val viewModel = FakeSportsViewModel()
        viewModel.filterFavorites(true)
        
        val filteredEvents = viewModel.events.value

        assertEquals(2, filteredEvents?.size)
        assert(filteredEvents?.all { it.isFavorite } ?: false)
    }

    @Test
    fun `filterFavorites should return all events when false`() {
        val viewModel = FakeSportsViewModel()
        viewModel.filterFavorites(false)
        
        val allEvents = viewModel.events.value

        assertEquals(4, allEvents?.size)
    }
}