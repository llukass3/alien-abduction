package com.example.alien_abduction.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete

@Dao
interface CustomLocationDao {
    @Insert
    suspend fun insert(location: CustomLocationEntity)

    @Delete
    suspend fun delete(location: CustomLocationEntity)

    @Query("SELECT * FROM custom_locations")
    suspend fun getAll(): List<CustomLocationEntity>
}