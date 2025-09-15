package com.example.alien_abduction.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.alien_abduction.domain.dataModels.GameData
import com.google.android.gms.maps.model.LatLng
import com.example.alien_abduction.domain.dataModels.PlayerGuess

class ResultScreenViewModel(val gameData: GameData) : ViewModel() {

    val alienLocation = LatLng(gameData.locationLatitude, gameData.locationLongitude)

}