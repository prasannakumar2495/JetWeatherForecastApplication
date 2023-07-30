package com.pk.jetweatherforecastapplication.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherObject(
	val description: String,
	val icon: String,
	val id: Int,
	val main: String,
)