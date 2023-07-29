package com.pk.jetweatherforecastapplication.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
					IconButton(onClick = { /*TODO*/ }) {
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