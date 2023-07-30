package com.pk.jetweatherforecastapplication.repo

import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.network.WeatherAPI
import com.pk.jetweatherforecastapplication.network.ktor.GetServiceKtor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepo @Inject constructor(
	private val api: WeatherAPI,
	private val service: GetServiceKtor,
) {
	suspend fun getWeather(city: String): Flow<Weather?> {
		return flow {
			emit(api.getWeather(city))
		}.flowOn(Dispatchers.IO)
	}
	
	suspend fun getWeatherKtor(city: String): Flow<Weather?> {
		return flow {
			emit(service.getWeatherKtor(query = city))
		}.flowOn(Dispatchers.IO)
	}
}