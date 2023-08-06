package com.pk.jetweatherforecastapplication.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.FavoriteBorder

object Constants {
	/**
	 * https://api.openweathermap.org/data/2.5/forecast/daily?q=bangalore&appid=ed60fcfbd110ee65c7150605ea8aceea&units=metric
	 */
	const val BASE_URL = "https://api.openweathermap.org/"
	const val METRIC = "metric"
	const val EMPTY = ""
	const val DEFAULT_LOCATION = "Rajahmundry"
	const val ABOUT = "About"
	const val FAVOURITES = "Favourites"
	const val SETTINGS = "Settings"
	
	// ICONS
	val BACK_ICON = Icons.Rounded.ArrowBack
	val FAVOURITE_ICON = Icons.Rounded.FavoriteBorder
	val DELETE_ICON = Icons.Rounded.Delete
}