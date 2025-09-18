package com.example.alien_abduction.data.repositoryImplementations

import com.example.alien_abduction.data.CustomLocationDao
import com.example.alien_abduction.data.CustomLocationEntity
import com.example.alien_abduction.domain.dataModels.CustomLocation
import com.example.alien_abduction.domain.repositories.CustomLocationRepository

class CustomLocationRepositoryImpl(
    private val dao: CustomLocationDao
): CustomLocationRepository {
    override suspend fun addLocation(location: CustomLocation) {
        dao.insert(
            CustomLocationEntity(
                name = location.name,
                latitude = location.latitude,
                longitude = location.longitude
            )
        )
    }
    override suspend fun removeLocation(location: CustomLocation) {
        // You may need to fetch the entity by name/coords if you don't have its id.
        val entities = dao.getAll()
        val entity = entities.find { it.name == location.name && it.latitude == location.latitude && it.longitude == location.longitude }
        if (entity != null) dao.delete(entity)
    }
    override suspend fun getAllLocations(): List<CustomLocation> =
        dao.getAll().map {
            CustomLocation(it.name, it.latitude, it.longitude)
        }
}