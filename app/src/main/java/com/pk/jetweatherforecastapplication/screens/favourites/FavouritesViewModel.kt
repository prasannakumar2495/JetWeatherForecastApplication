package com.pk.jetweatherforecastapplication.screens.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pk.jetweatherforecastapplication.MainActivity.Companion.TAG
import com.pk.jetweatherforecastapplication.model.Favourite
import com.pk.jetweatherforecastapplication.repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val weatherRepo: WeatherRepo) :
	ViewModel() {
	private val _favouritesList = MutableStateFlow<List<Favourite>>(emptyList())
	val favouritesList = _favouritesList.asStateFlow()
	
	init {
		viewModelScope.launch {
			weatherRepo.getFavourites().distinctUntilChanged()
				.collect {
					if (it.isEmpty())
						Log.d(TAG, "MSG: No Favourites")
					else _favouritesList.value = it
				}
		}
	}
	
	suspend fun insertFavourite(favourite: Favourite) = viewModelScope.launch {
		weatherRepo.insertFavourite(favourite = favourite)
	}
	
	suspend fun deleteSingleFavourite(favourite: Favourite) = viewModelScope.launch {
		weatherRepo.deleteSingleFavourite(favourite = favourite)
	}
	
	suspend fun updateFavourite(favourite: Favourite) = viewModelScope.launch {
		weatherRepo.updateFavourite(favourite = favourite)
	}
}