package com.pk.jetweatherforecastapplication.di

import android.util.Log
import com.pk.jetweatherforecastapplication.BuildConfig
import com.pk.jetweatherforecastapplication.network.WeatherAPI
import com.pk.jetweatherforecastapplication.network.ktor.GetServiceKtor
import com.pk.jetweatherforecastapplication.network.ktor.GetServiceKtorImpl
import com.pk.jetweatherforecastapplication.util.Constants.BASE_URL
import com.pk.jetweatherforecastapplication.util.Constants.HTTP_RESPONSE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
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
	fun createKtorClient(): HttpClient {
		val json = kotlinx.serialization.json.Json {
			ignoreUnknownKeys = true
			isLenient = true
			prettyPrint = true
		}
		return HttpClient(Android) {
			install(JsonFeature) {
				serializer = KotlinxSerializer(json)
			}
			engine {
				connectTimeout = 15
				socketTimeout = 15
			}
			Logging {
				level = LogLevel.ALL
			}
			ResponseObserver {
				Log.d(HTTP_RESPONSE, "createKtorClient: ${it.status.value}")
			}
			defaultRequest {
				contentType(ContentType.Application.Json)
				parameter("appid", BuildConfig.WEATHER_API_KEY)
			}
		}
	}
	
	@Singleton
	@Provides
	fun createKtorService(httpClient: HttpClient): GetServiceKtor {
		return GetServiceKtorImpl(httpClient)
	}
}