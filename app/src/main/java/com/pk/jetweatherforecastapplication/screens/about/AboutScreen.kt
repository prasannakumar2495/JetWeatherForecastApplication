package com.pk.jetweatherforecastapplication.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.pk.jetweatherforecastapplication.R
import com.pk.jetweatherforecastapplication.screens.main.MainScreenViewModel
import com.pk.jetweatherforecastapplication.util.Constants.ABOUT
import com.pk.jetweatherforecastapplication.widgets.WeatherAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController, mainScreenViewModel: MainScreenViewModel) {
	Scaffold(topBar = {
		WeatherAppBar(
			navController = navController, title = ABOUT,
			icon = Icons.Rounded.ArrowBack, isMainScreen = false,
			onButtonClicked = {
				navController.popBackStack()
			}, displayBackNavigationIcon = true
		)
	}) {
		Column(
			modifier = Modifier.padding(it),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(text = stringResource(id = R.string.app_name))
		}
	}
}