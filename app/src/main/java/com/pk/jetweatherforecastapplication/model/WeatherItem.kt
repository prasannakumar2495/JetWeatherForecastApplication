package com.pk.jetweatherforecastapplication.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherItem(
	@SerialName("clouds")
	val clouds: Int,
	@SerialName("deg")
	val deg: Int,
	@SerialName("dt")
	val dt: Int,
	@SerialName("feels_like")
	val feels_like: FeelsLike,
	@SerialName("gust")
	val gust: Double,
	@SerialName("humidity")
	val humidity: Int,
	@SerialName("pop")
	val pop: Double,
	@SerialName("pressure")
	val pressure: Int,
	@SerialName("rain")
	val rain: Double,
	@SerialName("speed")
	val speed: Double,
	@SerialName("sunrise")
	val sunrise: Int,
	@SerialName("sunset")
	val sunset: Int,
	@SerialName("temp")
	val temp: Temp,
	@SerialName("weather")
	val weather: List<WeatherObject>,
)