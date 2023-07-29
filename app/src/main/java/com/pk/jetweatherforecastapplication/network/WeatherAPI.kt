package com.pk.jetweatherforecastapplication.network

import com.pk.jetweatherforecastapplication.BuildConfig
import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.util.Constants.METRIC
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
	@GET(value = "/data/2.5/forecast/daily?")
	suspend fun getWeather(
		@Query("q") query: String,
		@Query("units") units: String = METRIC,
		@Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
	): Weather
}