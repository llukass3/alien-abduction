package com.example.alien_abduction.presentation.composables.screens.menu.AppInfo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UXDescription(modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier) {
        item {
            HeadLine("Hello Überschrift")
            BodyText(
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
                        "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
                        "vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                        "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                        "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et" +
                        " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
                        "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem " +
                        "ipsum dolor sit amet."
            )
            BodyText(
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
                        "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
                        "vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                        "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                        "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et" +
                        " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
                        "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem " +
                        "ipsum dolor sit amet."
            )
            HeadLine("Hello Überschrift")
            BodyText(
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
                        "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
                        "vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                        "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                        "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et" +
                        " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
                        "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem " +
                        "ipsum dolor sit amet."
            )
        }
    }
}