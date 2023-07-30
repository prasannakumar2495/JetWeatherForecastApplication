package com.pk.jetweatherforecastapplication.network.ktor

import com.pk.jetweatherforecastapplication.BuildConfig
import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.util.Constants.METRIC

interface GetServiceKtor {
	suspend fun getWeatherKtor(
		query: String,
		units: String = METRIC,
		apiKey: String = BuildConfig.WEATHER_API_KEY,
	): Weather?
}