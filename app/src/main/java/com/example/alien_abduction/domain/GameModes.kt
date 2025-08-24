package com.example.alien_abduction.domain

import androidx.annotation.StringRes
import com.example.alien_abduction.R

sealed interface GameModes {
    val name: String
    val mode: GameMode
    @get:StringRes val descriptionRes: Int

    companion object {

        /** returns the corresponding GameMode object for the given GameMode enum value */
        fun fromMode(mode: GameMode): GameModes = when(mode) {
            GameMode.CLASSIC -> Classic()
            GameMode.EXPLORE -> Explore()
            GameMode.MULTIPLAYER -> Multiplayer()
            GameMode.CHALLENGE -> Challenge()
        }
    }
}

data class Classic (
    override val name: String = "Klassisch",
    override val mode: GameMode = GameMode.CLASSIC,
    override val descriptionRes: Int = R.string.game_mode_classic_description,
    val countdown: Float = 240f
) : GameModes

data class Explore (
    override val name: String = "Erkunden",
    override val mode: GameMode = GameMode.EXPLORE,
    override val descriptionRes: Int = R.string.game_mode_explore_description,
) : GameModes

data class Multiplayer (
    override val name: String = "Mehrspieler",
    override val mode: GameMode = GameMode.MULTIPLAYER,
    override val descriptionRes: Int = R.string.game_mode_multiplayer_description,
    val countdown: Float = 240f,
    var numberOfPlayers: Int = 2
) : GameModes

data class Challenge (
    override val name: String = "Herausforderung",
    override val mode: GameMode = GameMode.CHALLENGE,
    override val descriptionRes: Int = R.string.game_mode_challenge_description,
    val challenges: List<CustomChallenge> = listOf(),
) : GameModes

