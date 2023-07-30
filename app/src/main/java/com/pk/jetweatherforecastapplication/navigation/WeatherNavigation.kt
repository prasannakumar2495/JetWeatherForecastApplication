package com.pk.jetweatherforecastapplication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pk.jetweatherforecastapplication.screens.about.AboutScreen
import com.pk.jetweatherforecastapplication.screens.favourites.FavouritesScreen
import com.pk.jetweatherforecastapplication.screens.main.MainScreen
import com.pk.jetweatherforecastapplication.screens.main.MainScreenViewModel
import com.pk.jetweatherforecastapplication.screens.search.SearchScreen
import com.pk.jetweatherforecastapplication.screens.settings.SettingsScreen
import com.pk.jetweatherforecastapplication.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
		composable(route = WeatherScreens.SplashScreen.name) {
			WeatherSplashScreen(navController = navController)
		}
		val mainScreenRoute = WeatherScreens.MainScreen.name
		composable(route = "$mainScreenRoute/{city}", arguments = listOf(
			navArgument(name = "city") {
				type = NavType.StringType
			}
		)) { navBack ->
			navBack.arguments?.getString("city")?.let { city ->
				val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
				MainScreen(navController = navController, mainScreenViewModel, cityName = city)
			}
		}
		composable(route = WeatherScreens.SearchScreen.name) {
			val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
			SearchScreen(navController = navController, mainScreenViewModel)
		}
		composable(route = WeatherScreens.AboutScreen.name) {
			val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
			AboutScreen(navController = navController, mainScreenViewModel)
		}
		composable(route = WeatherScreens.FavouritesScreen.name) {
			val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
			FavouritesScreen(navController = navController, mainScreenViewModel)
		}
		composable(route = WeatherScreens.SettingsScreen.name) {
			val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
			SettingsScreen(navController = navController, mainScreenViewModel)
		}
	}
}