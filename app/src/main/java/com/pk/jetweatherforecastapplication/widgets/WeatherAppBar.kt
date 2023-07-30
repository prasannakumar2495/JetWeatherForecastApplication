package com.pk.jetweatherforecastapplication.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pk.jetweatherforecastapplication.MainActivity.Companion.TAG
import com.pk.jetweatherforecastapplication.navigation.WeatherScreens
import com.pk.jetweatherforecastapplication.util.Constants.ABOUT
import com.pk.jetweatherforecastapplication.util.Constants.FAVOURITES
import com.pk.jetweatherforecastapplication.util.Constants.SETTINGS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
	title: String = "Title",
	icon: ImageVector? = null,
	isMainScreen: Boolean = true,
	elevation: Dp = 0.dp,
	navController: NavController,
	onAddActionClicked: () -> Unit = {},
	onButtonClicked: () -> Unit = {},
	displayBackNavigationIcon: Boolean = false,
) {
	Surface(
		shadowElevation = elevation
	) {
		val showDialogState = remember {
			mutableStateOf(false)
		}
		if (showDialogState.value) {
			ShowDialogue(showDialogState, navController)
		}
		TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
			title = {
				Text(
					text = title,
					color = Color.Black,
					style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
				)
			},
			actions = {
				if (isMainScreen) {
					IconButton(onClick = { onAddActionClicked.invoke() }) {
						Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
					}
					IconButton(onClick = {
						showDialogState.value = !showDialogState.value
					}) {
						Icon(
							imageVector = Icons.Rounded.MoreVert, contentDescription = "More Option"
						)
					}
				} else Box {}
			},
			navigationIcon = {
				if (displayBackNavigationIcon)
					icon?.let {
						Icon(imageVector = it,
							contentDescription = "Navigation Button",
							modifier = Modifier.clickable {
								onButtonClicked.invoke()
							})
					}
			})
	}
}

@Composable
fun ShowDialogue(showDialogState: MutableState<Boolean>, navController: NavController) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentSize(Alignment.TopEnd)
			.absolutePadding(top = 45.dp, right = 20.dp)
	) {
		val itemsList = HashMap<ImageVector, String>()
		itemsList[Icons.Rounded.AccountBox] = ABOUT
		itemsList[Icons.Rounded.Favorite] = FAVOURITES
		itemsList[Icons.Rounded.Settings] = SETTINGS
		DropdownMenu(
			expanded = showDialogState.value,
			onDismissRequest = { showDialogState.value = !showDialogState.value },
			modifier = Modifier
				.width(140.dp)
				.background(Color.White)
		) {
			itemsList.forEach { hashMap ->
				Log.d(TAG, "ShowDialogue: $hashMap")
				DropdownMenuItem(text = { Text(text = hashMap.value) }, leadingIcon = {
					Icon(
						imageVector = hashMap.key,
						contentDescription = null
					)
				}, onClick = {
					showDialogState.value = !showDialogState.value
					navController.navigate(
						when (hashMap.value) {
							ABOUT -> WeatherScreens.AboutScreen.name
							FAVOURITES -> WeatherScreens.FavouritesScreen.name
							SETTINGS -> WeatherScreens.SettingsScreen.name
							else -> WeatherScreens.MainScreen.name
						}
					)
				})
			}
		}
	}
}
