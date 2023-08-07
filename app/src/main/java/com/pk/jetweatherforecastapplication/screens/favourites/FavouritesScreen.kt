package com.pk.jetweatherforecastapplication.screens.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pk.jetweatherforecastapplication.model.Favourite
import com.pk.jetweatherforecastapplication.util.Constants.BACK_ICON
import com.pk.jetweatherforecastapplication.util.Constants.DELETE_ICON
import com.pk.jetweatherforecastapplication.util.Constants.FAVOURITES
import com.pk.jetweatherforecastapplication.widgets.WeatherAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(navController: NavController, favouritesViewModel: FavouritesViewModel) {
	Column(
		horizontalAlignment = CenterHorizontally,
		verticalArrangement = Center
	) {
		Scaffold(
			topBar = {
				WeatherAppBar(
					navController = navController,
					title = FAVOURITES,
					icon = BACK_ICON,
					isMainScreen = false, displayBackNavigationIcon = true,
					onButtonClicked = { navController.popBackStack() })
			}
		) { paddingValues ->
			Surface(modifier = Modifier.padding(paddingValues)) {
				Column(verticalArrangement = Center, horizontalAlignment = CenterHorizontally) {
					val listOfFavourites = favouritesViewModel.favouritesList.collectAsState().value
					LazyColumn {
						items(items = listOfFavourites, key = { it.city }) { favouriteItem ->
							CityRow(navController, favouriteItem, favouritesViewModel)
						}
					}
				}
			}
		}
	}
}

@Composable
fun CityRow(
	navController: NavController,
	favouriteItem: Favourite,
	favouritesViewModel: FavouritesViewModel,
) {
	Surface(
		modifier = Modifier
			.padding(10.dp)
			.fillMaxWidth()
			.height(50.dp)
			.clickable { },
		shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp, bottomStart = 8.dp),
		color = Color(0xFFB2EBF2)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 15.dp, vertical = 10.dp),
			verticalAlignment = CenterVertically,
			horizontalArrangement = SpaceBetween
		) {
			Text(
				text = favouriteItem.city,
				color = Color.Black,
			)
			Surface(
				shape = CircleShape,
				color = Color(0xFFDCEDC8)
			) {
				Text(
					modifier = Modifier.padding(4.dp),
					text = favouriteItem.country,
					color = Color.Black,
				)
			}
			IconButton(onClick = {
				CoroutineScope(Dispatchers.Main).launch {
					favouritesViewModel.apply {
						withContext(context = Dispatchers.IO) {
							deleteSingleFavourite(favourite = favouriteItem)
						}
						withContext(context = Dispatchers.IO) {
							getAllFavourites()
						}
					}
				}
			}, content = {
				Icon(
					imageVector = DELETE_ICON,
					contentDescription = "Delete Icon",
					tint = Color.Red.copy(alpha = 0.7f)
				)
			})
		}
	}
}