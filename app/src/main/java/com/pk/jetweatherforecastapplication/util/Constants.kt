package com.pk.jetweatherforecastapplication.util

object Constants {
	/**
	 * https://api.openweathermap.org/data/2.5/forecast/daily?q=bangalore&appid=ed60fcfbd110ee65c7150605ea8aceea&units=metric
	 */
	const val BASE_URL = "https://api.openweathermap.org/"
	const val BASE_URL_KTOR = "https://api.openweathermap.org/data/2.5/forecast/daily?"
	const val METRIC = "metric"
	const val EMPTY = ""
	const val DEFAULT_LOCATION = "Rajahmundry"
	const val ABOUT = "About"
	const val FAVOURITES = "Favourites"
	const val SETTINGS = "Settings"
	const val HTTP_RESPONSE = "Http Response"
}