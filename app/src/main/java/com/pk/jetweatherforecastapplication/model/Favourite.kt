package com.pk.jetweatherforecastapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(
	@PrimaryKey
	val city: String,
	val country: String,
)
