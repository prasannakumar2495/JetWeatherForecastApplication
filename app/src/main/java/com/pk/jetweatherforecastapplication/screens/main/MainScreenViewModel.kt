package com.pk.jetweatherforecastapplication.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pk.jetweatherforecastapplication.data.DataOrException
import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repo: WeatherRepo) : ViewModel() {
	val weatherData: MutableStateFlow<DataOrException<Weather>> =
		MutableStateFlow(DataOrException.Loading())
	
	fun loadWeather(city: String) {
		viewModelScope.launch {
			if (city.isEmpty()) return@launch
			weatherData.value = DataOrException.Loading()
			repo.getWeather(city = city).catch {
				weatherData.value =
					DataOrException.Error(message = it.message.toString())
			}
				.collect {
					weatherData.value = DataOrException.Success(it)
					Log.d("TAG", "loadWeather: $it")
				}
		}
	}
}