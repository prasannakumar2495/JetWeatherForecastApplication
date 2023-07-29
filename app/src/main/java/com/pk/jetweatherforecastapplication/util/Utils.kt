package com.pk.jetweatherforecastapplication.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timeStamp: Int, country: String): String {
	val sdf = SimpleDateFormat("E, MMM dd yyyy", Locale("en", country))
	val data = Date(timeStamp.toLong() * 1000)
	return sdf.format(data)
}

fun formatDateForDay(timeStamp: Int, country: String): String {
	val sdf = SimpleDateFormat("E", Locale("en", country))
	val data = Date(timeStamp.toLong() * 1000)
	return sdf.format(data)
}

fun formatTime(timeStamp: Int, country: String): String {
	val sdf = SimpleDateFormat("HH:mm aaa z", Locale("en", country))
	val data = Date(timeStamp.toLong() * 1000)
	return sdf.format(data)
}