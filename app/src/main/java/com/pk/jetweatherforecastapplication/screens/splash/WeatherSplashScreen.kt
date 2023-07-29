package com.pk.jetweatherforecastapplication.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pk.jetweatherforecastapplication.R
import com.pk.jetweatherforecastapplication.navigation.WeatherScreens
import com.pk.jetweatherforecastapplication.util.Constants.DEFAULT_LOCATION
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController) {
	val scale = remember {
		Animatable(0f)
	}
	
	LaunchedEffect(key1 = true, block = {
		scale.animateTo(targetValue = 0.9f, animationSpec = tween(
			durationMillis = 1000, easing = {
				OvershootInterpolator(8f).getInterpolation(it)
			}
		))
		delay(2000L)
		
		navController.navigate(WeatherScreens.MainScreen.name + "/$DEFAULT_LOCATION")
	})
	
	Surface(
		modifier = Modifier
			.padding(15.dp)
			.size(300.dp)
			.scale(scale = scale.value),
		shape = CircleShape,
		color = Color.White,
		border = BorderStroke(width = 2.dp, color = Color.LightGray),
	) {
		Column(
			modifier = Modifier.padding(1.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Image(
				painter = painterResource(R.drawable.weather),
				modifier = Modifier.size(180.dp),
				contentScale = ContentScale.Fit,
				contentDescription = "This is the application icon"
			)
			Text(
				text = "Find the sun?",
				style = MaterialTheme.typography.headlineMedium,
				color = Color.LightGray
			)
		}
	}
}