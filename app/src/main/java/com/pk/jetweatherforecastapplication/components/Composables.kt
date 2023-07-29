package com.pk.jetweatherforecastapplication.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pk.jetweatherforecastapplication.R
import com.pk.jetweatherforecastapplication.model.Weather
import com.pk.jetweatherforecastapplication.model.WeatherItem
import com.pk.jetweatherforecastapplication.ui.theme.customColor
import com.pk.jetweatherforecastapplication.util.formatDateForDay
import com.pk.jetweatherforecastapplication.util.formatTime

@Composable
fun WeatherStateImage(iconUrl: String, modifier: Modifier) {
	Image(
		painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${iconUrl}.png"),
		contentDescription = "Weather Image",
		modifier = modifier,
	)
}

@Composable
fun HumidityWindPressureRow(weatherDataState: Weather) {
	Row(
		modifier = Modifier
			.padding(16.dp, 8.dp)
			.fillMaxWidth(),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		weatherDataState.list[0].apply {
			HumidityWindPressureRowItem("${humidity}%", R.drawable.humidity)
			HumidityWindPressureRowItem("$pressure psi", R.drawable.pressure)
			HumidityWindPressureRowItem("$speed kmph", R.drawable.wind)
		}
	}
}

@Composable
fun HumidityWindPressureRowItem(weatherDataState: String, icon: Int) {
	Row {
		Icon(
			painter = painterResource(id = icon),
			contentDescription = "Humidity", modifier = Modifier.size(20.dp)
		)
		Spacer(modifier = Modifier.size(8.dp))
		Text(
			text = weatherDataState,
			style = MaterialTheme.typography.labelMedium
		)
	}
}

@Composable
fun SunSetSunRiseRow(weatherDataState: Weather) {
	Row(
		Modifier
			.padding(16.dp)
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		SunSetSunRiseRowItem(
			formatTime(weatherDataState.list[0].sunrise, weatherDataState.city.country),
			R.drawable.icons8_sunrise_50
		)
		SunSetSunRiseRowItem(
			formatTime(weatherDataState.list[0].sunset, weatherDataState.city.country),
			R.drawable.icons8_sunset_50
		)
	}
}

@Composable
fun SunSetSunRiseRowItem(time: String, icon: Int) {
	Row(verticalAlignment = Alignment.CenterVertically) {
		Icon(
			painter = painterResource(id = icon),
			contentDescription = "Sunset/Sunrise Icon",
			modifier = Modifier.size(20.dp)
		)
		Spacer(modifier = Modifier.size(10.dp))
		Text(text = time)
	}
}

@Composable
fun WeeksData(weatherDataState: Weather) {
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.padding(4.dp),
		shape = RoundedCornerShape(8.dp),
		color = Color.LightGray
	) {
		LazyColumn(content = {
			items(weatherDataState.list) {
				WeeksDataItem(data = it)
			}
		}, contentPadding = PaddingValues(4.dp))
	}
}

@Composable
fun WeeksDataItem(data: WeatherItem) {
	Surface(
		modifier = Modifier.padding(4.dp, 2.dp),
		shape = RoundedCornerShape(12.dp, 0.dp, 12.dp, 12.dp)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(4.dp),
			horizontalArrangement = Arrangement.SpaceAround,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(text = formatDateForDay(data.dt, "IN"))
			WeatherStateImage(iconUrl = data.weather[0].icon, modifier = Modifier.size(50.dp))
			Surface(color = customColor, shape = RoundedCornerShape(12.dp)) {
				Text(text = data.weather[0].description, modifier = Modifier.padding(4.dp))
			}
			Text(text = buildAnnotatedString {
				withStyle(
					style = SpanStyle(color = Color.Blue)
				) {
					append(data.temp.max.toString() + "\u2103" + " ")
				}
				
				withStyle(style = SpanStyle(color = Color.Black)) {
					append(data.temp.min.toString() + "\u2103")
				}
			})
		}
	}
	Spacer(modifier = Modifier.size(4.dp))
}
