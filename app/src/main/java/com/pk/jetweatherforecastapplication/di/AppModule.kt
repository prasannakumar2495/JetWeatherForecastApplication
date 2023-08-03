package com.pk.jetweatherforecastapplication.di

import android.content.Context
import androidx.room.Room
import com.pk.jetweatherforecastapplication.data.WeatherDao
import com.pk.jetweatherforecastapplication.network.WeatherAPI
import com.pk.jetweatherforecastapplication.repo.WeatherRoomDB
import com.pk.jetweatherforecastapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
	@Provides
	@Singleton
	fun provideWeatherAPI(): WeatherAPI = Retrofit.Builder()
		.baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
		.build().create(WeatherAPI::class.java)
	
	@Singleton
	@Provides
	fun provideWeatherDao(weatherRoomDB: WeatherRoomDB): WeatherDao = weatherRoomDB.weatherDao()
	
	@Singleton
	@Provides
	fun provideWeatherRoomDB(@ApplicationContext context: Context): WeatherRoomDB =
		Room.databaseBuilder(
			context, WeatherRoomDB::class.java, "Weather Room DB"
		).fallbackToDestructiveMigration().build()
}