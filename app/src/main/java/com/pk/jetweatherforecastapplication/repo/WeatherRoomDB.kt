package com.pk.jetweatherforecastapplication.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pk.jetweatherforecastapplication.data.WeatherDao
import com.pk.jetweatherforecastapplication.model.Favourite

@Database(entities = [Favourite::class], version = 1, exportSchema = false)
abstract class WeatherRoomDB : RoomDatabase() {
	abstract fun weatherDao(): WeatherDao
}