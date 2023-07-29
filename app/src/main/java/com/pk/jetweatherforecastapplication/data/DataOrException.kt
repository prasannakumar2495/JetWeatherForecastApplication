package com.pk.jetweatherforecastapplication.data

sealed class DataOrException<T>(
	val data: T? = null,
	val message: String? = null,
) {
	class Success<T>(data: T) : DataOrException<T>(data = data)
	class Error<T>(message: String) : DataOrException<T>(message = message)
	class Loading<T> : DataOrException<T>()
}