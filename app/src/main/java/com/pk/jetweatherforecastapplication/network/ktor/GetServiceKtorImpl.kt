package com.pk.jetweatherforecastapplication.network.ktor

import android.util.Log
import com.pk.jetweatherforecastapplication.MainActivity.Companion.TAG
import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.util.Constants.BASE_URL_KTOR
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class GetServiceKtorImpl(
	private val client: HttpClient,
) : GetServiceKtor {
	override suspend fun getWeatherKtor(query: String, units: String, apiKey: String): Weather? {
		return try {
			client.get {
				url(BASE_URL_KTOR)
				parameter("q", query)
				parameter("appid", apiKey)
				parameter("units", units)
			}
		}
		/**
		 * Below we are just demonstrating the type of error types that can be accessed.
		 * It's Ok to write single error type.
		 */
		catch (e: RedirectResponseException) {
			//3xx - response
			Log.d(TAG, "getWeather: ${e.response.status.description}")
			null
		} catch (e: ClientRequestException) {
			//4xx - response
			Log.d(TAG, "getWeather: ${e.response.status.description}")
			null
		} catch (e: ServerResponseException) {
			//5xx - response
			Log.d(TAG, "getWeather: ${e.response.status.description}")
			null
		} catch (e: Exception) {
			Log.d(TAG, "getWeather: ${e.message}")
			null
		}
	}
}