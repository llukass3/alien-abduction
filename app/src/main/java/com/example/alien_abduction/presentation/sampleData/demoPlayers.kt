package com.example.alien_abduction.presentation.sampleData

import com.example.alien_abduction.domain.PlayerSlot
import com.example.alien_abduction.domain.dataModels.Player

val demoPlayerList = listOf (
    Player(
        slot = PlayerSlot.PLAYER_1,
        nickname = "Lukas"
    ),
    Player(
        slot = PlayerSlot.PLAYER_2,
        nickname = "Beyoncé"
    ),
    Player(
        slot = PlayerSlot.PLAYER_3,
        nickname = "Kelly"
    ),
    Player(
        slot = PlayerSlot.PLAYER_4,
        nickname = "Michelle"
    )
)