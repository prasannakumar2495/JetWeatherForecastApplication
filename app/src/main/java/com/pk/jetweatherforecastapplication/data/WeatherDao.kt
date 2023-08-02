package com.pk.jetweatherforecastapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pk.jetweatherforecastapplication.model.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
	@Query("select * from favourite")
	suspend fun getFavourites(): Flow<List<Favourite>>
	
	@Query("select * from favourite where city=:city")
	suspend fun getFavouriteByCity(city: String): Favourite
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertFavourite(favourite: Favourite)
	
	@Update(onConflict = OnConflictStrategy.REPLACE)
	suspend fun updateFavourite(favourite: Favourite)
	
	@Query("delete from favourite")
	suspend fun deleteAllFavourite()
	
	@Delete
	suspend fun deleteSingleFavourite(favourite: Favourite)
}