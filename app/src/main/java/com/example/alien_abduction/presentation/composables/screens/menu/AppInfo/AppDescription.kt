package com.example.alien_abduction.presentation.composables.screens.menu.AppInfo

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppDescription(modifier: Modifier = Modifier) {

    LazyColumn(modifier = Modifier) {
        item {
            HeadLine("Hello Überschrift")
            BodyText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
                    "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
                    "vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                    "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                    "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et" +
                    " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
                    "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem " +
                    "ipsum dolor sit amet.")
            BodyText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
                    "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
                    "vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                    "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                    "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et" +
                    " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
                    "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem " +
                    "ipsum dolor sit amet.")
            HeadLine("Hello Überschrift")
            BodyText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod " +
                    "tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At " +
                    "vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                    "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                    "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et" +
                    " dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo " +
                    "dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem " +
                    "ipsum dolor sit amet.")
        }

    }
}