package com.dmribeiro87.kaizenapp.gamesFeature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmribeiro87.kaizenapp.core.util.Resource
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports
import com.dmribeiro87.kaizenapp.gamesFeature.domain.usecase.GetSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getSportsUseCase: GetSportsUseCase
): ViewModel(){

    private var _sportsData = MutableLiveData<Resource<List<Sports>>>()
    val sportsData: LiveData<Resource<List<Sports>>> = _sportsData

    fun getSportsEvents() {
        viewModelScope.launch {
            _sportsData.value = Resource.Loading()
            val resource = getSportsUseCase()
            _sportsData.value = when(resource){
                is Resource.Success -> Resource.Success(resource.data)
                is Resource.Error -> Resource.Error(resource.message, resource.data)
                else -> Resource.Loading()
            }
        }
    }

}