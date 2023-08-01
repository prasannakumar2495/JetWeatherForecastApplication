package com.pk.jetweatherforecastapplication.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
	@SerialName("city")
	val city: City,
	@SerialName("cnt")
	val cnt: Int,
	@SerialName("cod")
	val cod: String,
	@SerialName("list")
	val list: List<WeatherItem>,
	@SerialName("message")
	val message: Double,
)