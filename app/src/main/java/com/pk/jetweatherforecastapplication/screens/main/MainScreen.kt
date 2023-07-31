package com.pk.jetweatherforecastapplication.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pk.jetweatherforecastapplication.components.HumidityWindPressureRow
import com.pk.jetweatherforecastapplication.components.SunSetSunRiseRow
import com.pk.jetweatherforecastapplication.components.WeatherStateImage
import com.pk.jetweatherforecastapplication.components.WeeksData
import com.pk.jetweatherforecastapplication.data.DataOrException
import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.navigation.WeatherScreens
import com.pk.jetweatherforecastapplication.ui.theme.customColor
import com.pk.jetweatherforecastapplication.util.Constants.DEFAULT_LOCATION
import com.pk.jetweatherforecastapplication.util.formatDate
import com.pk.jetweatherforecastapplication.widgets.WeatherAppBar

@Composable
fun MainScreen(
	navController: NavController,
	viewModel: MainScreenViewModel = hiltViewModel(),
	cityName: String = DEFAULT_LOCATION,
) {
	val weatherDataState = viewModel.weatherData.collectAsState()
	LaunchedEffect(Unit) {
		viewModel.loadWeatherKtor(city = cityName)
		//viewModel.loadWeather(city = cityName)
	}
	
	when (weatherDataState.value) {
		is DataOrException.Loading -> CircularProgressIndicator()
		is DataOrException.Success -> {
			weatherDataState.value.data?.let {
				MainScaffold(it, navController)
			}
		}
		
		is DataOrException.Error -> {
			Text(text = weatherDataState.value.message ?: "Error")
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
	weatherDataState: Weather,
	navController: NavController,
) {
	Scaffold(topBar = {
		WeatherAppBar(
			title = weatherDataState.city.name + ", ${weatherDataState.city.country}",
			navController = navController,
			elevation = 5.dp, icon = Icons.Rounded.ArrowBack,
			onAddActionClicked = { navController.navigate(WeatherScreens.SearchScreen.name) }
		)
	}, content = {
		MainContent(it, weatherDataState)
	})
}

@Composable
fun MainContent(paddingValues: PaddingValues, weatherDataState: Weather) {
	Column(
		modifier = Modifier
			.padding(paddingValues)
			.fillMaxSize(),
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = formatDate(
				weatherDataState.list[0].dt,
				country = weatherDataState.city.country
			),
			style = MaterialTheme.typography.bodyMedium,
			fontWeight = FontWeight.Bold,
			modifier = Modifier.padding(10.dp)
		)
		Surface(
			modifier = Modifier.size(180.dp),
			shape = CircleShape,
			color = customColor
		) {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.padding(10.dp)
			) {
				WeatherStateImage(
					weatherDataState.list[0].weather[0].icon,
					modifier = Modifier.size(50.dp)
				)
				Text(
					text = weatherDataState.list[0].temp.day.toString() + "\u2103",
					style = MaterialTheme.typography.headlineMedium,
					fontWeight = FontWeight.Bold
				)
				Text(
					text = weatherDataState.list[0].weather[0].main, fontStyle = FontStyle.Italic,
					fontWeight = FontWeight.SemiBold
				)
			}
		}
		HumidityWindPressureRow(weatherDataState)
		Divider()
		SunSetSunRiseRow(weatherDataState)
		Text(
			text = "This Week",
			fontWeight = FontWeight.Bold,
			style = MaterialTheme.typography.bodyLarge
		)
		WeeksData(weatherDataState)
	}
}