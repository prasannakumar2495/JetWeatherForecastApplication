package com.pk.jetweatherforecastapplication.repo

import com.pk.jetweatherforecastapplication.data.WeatherDao
import com.pk.jetweatherforecastapplication.model.Favourite
import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.network.WeatherAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepo @Inject constructor(
	private val api: WeatherAPI,
	private val weatherDao: WeatherDao,
) {
	suspend fun getWeather(city: String): Flow<Weather> {
		return flow {
			emit(api.getWeather(city))
		}.flowOn(Dispatchers.IO)
	}
	
	suspend fun getFavourites(): Flow<List<Favourite>> =
		flow { emit(weatherDao.getFavourites()) }
	
	suspend fun insertFavourite(favourite: Favourite) = weatherDao.insertFavourite(favourite)
	suspend fun updateFavourite(favourite: Favourite) = weatherDao.updateFavourite(favourite)
	suspend fun deleteAllFavourites() = weatherDao.deleteAllFavourite()
	suspend fun deleteSingleFavourite(favourite: Favourite) =
		weatherDao.deleteSingleFavourite(favourite)
	
	suspend fun getFavouriteByCity(city: String) = weatherDao.getFavouriteByCity(city)
}