package com.pk.jetweatherforecastapplication.di

import com.pk.jetweatherforecastapplication.network.WeatherAPI
import com.pk.jetweatherforecastapplication.network.ktor.GetServiceKtor
import com.pk.jetweatherforecastapplication.network.ktor.GetServiceKtorImpl
import com.pk.jetweatherforecastapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
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
	
	@Provides
	@Singleton
	fun createKtorClient(): GetServiceKtor {
		return GetServiceKtorImpl(
			client = HttpClient(Android) {
				install(Logging) {
					level = LogLevel.ALL
				}
				install(JsonFeature) {
					serializer = KotlinxSerializer()
				}
			}
		)
	}
}