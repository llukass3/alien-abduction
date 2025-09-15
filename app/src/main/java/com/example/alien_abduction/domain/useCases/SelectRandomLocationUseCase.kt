package com.example.alien_abduction.domain.useCases

import com.example.alien_abduction.BuildConfig
import com.example.alien_abduction.domain.dataModels.StreetViewLocation
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.Status
import com.google.maps.android.StreetViewUtils.Companion.fetchStreetViewData

/** searches for random unused location, checks streetView availability and assigns it as current location */
class SelectRandomLocationUseCase {

    suspend operator fun invoke(
        streetViewLocations: List<StreetViewLocation>,
        usedLocationIds: MutableList<String>,
        updateValues: (status: Status, usedId: String?) -> Unit
    ): LatLng? {

        var randomLocation: LatLng? = null
        var usedLocationId: String? = null
        var streetViewStatus = Status.NOT_FOUND

        while(true) {
            //select random location from list
            val candidate = streetViewLocations.random()

            //check if location has already been used
            if(!usedLocationIds.contains(candidate.id)) {
                usedLocationId = candidate.id

                //convert candidate to LatLng format
                val candidateLatLng = LatLng(candidate.latitude, candidate.longitude)

                //check whether street view data is available for the location
                streetViewStatus = fetchStreetViewData(candidateLatLng, BuildConfig.MAPS_API_KEY)
                if(streetViewStatus == Status.OK) {
                    //assign candidate as current location
                    randomLocation = LatLng(candidate.latitude, candidate.longitude)
                    break
                }
            }
        }
        updateValues(streetViewStatus, usedLocationId)
        return randomLocation
    }

}