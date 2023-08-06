@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.pk.jetweatherforecastapplication.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pk.jetweatherforecastapplication.MainActivity.Companion.TAG
import com.pk.jetweatherforecastapplication.navigation.WeatherScreens
import com.pk.jetweatherforecastapplication.screens.main.MainScreenViewModel
import com.pk.jetweatherforecastapplication.util.Constants.BACK_ICON
import com.pk.jetweatherforecastapplication.util.Constants.EMPTY
import com.pk.jetweatherforecastapplication.widgets.WeatherAppBar

@Composable
fun SearchScreen(
	navController: NavController,
	mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
) {
	Scaffold(topBar = {
		WeatherAppBar(
			title = "Search Here", navController = navController, isMainScreen = false,
			icon = BACK_ICON, displayBackNavigationIcon = true, onButtonClicked = {
				navController.popBackStack()
				Log.d(TAG, "SearchScreen: Clicked on back button in Search Screen.")
			}
		)
	}) { paddingValues ->
		Surface(modifier = Modifier.padding(paddingValues)) {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				SearchField(
					modifier = Modifier
						.fillMaxWidth()
						.padding(16.dp)
						.align(Alignment.CenterHorizontally)
				) {
					val mainScreenRoute = WeatherScreens.MainScreen.name
					navController.navigate("$mainScreenRoute/$it")
				}
			}
		}
	}
}

@Composable
fun SearchField(
	modifier: Modifier = Modifier,
	onSearch: (String) -> Unit = {},
) {
	/**
	 *  rememberSaveable => this will help in storing the value, during the screen rotation.
	 *  it will not loose the data that has been assigned.
	 */
	val searchQuery = rememberSaveable {
		mutableStateOf("")
	}
	val keyboardType = LocalSoftwareKeyboardController.current
	val isValidSearchQuery = remember(searchQuery.value) { searchQuery.value.trim().isNotEmpty() }
	Column {
		CommonTextField(
			valueState = searchQuery,
			placeHolder = "Search Here",
			onAction = KeyboardActions {
				if (!isValidSearchQuery) return@KeyboardActions
				onSearch(searchQuery.value.trim())
				searchQuery.value = EMPTY
				keyboardType?.hide()
			},
		)
		Log.d(TAG, "SearchField: ${searchQuery.value}")
	}
}

@Composable
fun CommonTextField(
	valueState: MutableState<String>,
	placeHolder: String,
	onAction: KeyboardActions = KeyboardActions.Default,
	imeAction: ImeAction = ImeAction.Next,
	keyboardType: KeyboardType = KeyboardType.Text,
) {
	OutlinedTextField(
		value = valueState.value,
		onValueChange = {
			valueState.value = it
		},
		maxLines = 1,
		singleLine = true,
		label = { Text(text = placeHolder) },
		keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
		keyboardActions = onAction,
		colors = TextFieldDefaults.outlinedTextFieldColors(
			focusedBorderColor = Color.Blue, cursorColor = Color.Black
		),
		shape = RoundedCornerShape(15.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(start = 10.dp, end = 10.dp)
	)
}
