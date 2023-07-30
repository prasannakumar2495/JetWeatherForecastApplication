package com.pk.jetweatherforecastapplication.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.pk.jetweatherforecastapplication.screens.main.MainScreenViewModel
import com.pk.jetweatherforecastapplication.util.Constants.SETTINGS

@Composable
fun SettingsScreen(navController: NavController, mainScreenViewModel: MainScreenViewModel) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(text = SETTINGS)
	}
}