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
            HeadLine("Inhaltliche Beschreibung")
            BodyText("Grundsätzlich hat die App vier Spielmodi: Klassisch, Erkunden, Mehrspieler und Herausforderung. Alle Spielmodi greifen das klassische GeoGuessr-Prinzip auf. Der klassische Modus hat einen Countdown, während der Erkunden-Modus zeitlich unbegrenzt ist. Im Multiplayer können bis zu 4 User nacheinander auf einem Gerät spielen. Der Herausforderungs-Modus bietet die Möglichkeit, eigene Szenarien zu erstellen, die anschließend von Freunden gespielt werden können. Nach jedem Spiel werden die Ergebnisse inklusive Punktzahl auf einem „Result-Screen“ angezeigt.")
            BodyText("Neben dem eigentliche Spiel gibt es noch einen Spielverlauf, in welchem vergangene Spiele angezeigt werden, ein Profil-Screen, der Statistiken anzeigt, sowie einen Achievement-Screen, wo der User seine verdienten Medaillen bestaunen kann.")

            HeadLine("Technische Beschreibung")
            BodyText("Das Spiel folgt dem sogenannten „Clean Architecture“ Prinzip von Google: Der Code ist in die drei Ebenen „data“, „domain“ und „presentation“ aufgeteilt. „Data“ definiert den Zugriff auf Datensätze wie den Street-View Standorten oder dem Spielverlauf. „Domain“ beherbergt die Geschäftslogik, also der gesamte Code, der sich mit der funktionalen Logik der App befasst hierzu gehören Datenmodelle, Use Cases und Funktionen für die Formatierung von Strings. Das „presentation“ Layer umfasst den gesamten UI-Technischen Code, also die Compose-Screens und View Models.")
            BodyText("Insgesamt habe ich auf eine säuberliche Trennung von UI- und Geschäftslogik geachtet, sodass in den Composables nur UI basierter Code zu finden ist, und alles andere über das View Model geregelt wird, welches als Schnittstelle mit dem Domain- und Data-Layer kommuniziert und der UI sogenannte „State“ Variablen bereitstellt, welche von der UI observiert werden und ihr ermöglichen, sich neu zu generieren (recompose), wenn eine „state“ Variable ihren Status ändert.")
            BodyText("Für die Einbindung von StreetView und Maps Funktionen greift die App auf das Google Maps SDK für Android zurück. Das nahtlose Wechseln zwischen den Screens ermöglicht die Type Safe Navigation Bibliothek von Android. In der MainActivity werden alle Screens definiert und mit allen benötigten Parametern (z.B. ViewModels) ausgestattet. Der NavHost kann dann in Callback Funktionen angesteuert werden und bei Bedarf zwischen den Screens navigieren. Das Speichern von Daten, welche auch nach dem Schließen der App erhalten bleiben sollen, werden Room-Databases verwendet. Dies ist ein auf Android zugeschnittenes Framework, welches auf SQLite basiert.")

            HeadLine("Was hat es nicht ins Spiel geschafft?")
            BodyText("Leider musste ich aus Zeitgründen einige Features auslassen: Ich habe geplant, einen Screen mit Achievements einzubauen. Hier hätte der User sich durch hohe Punktzahlen oder ähnlichen Challenges Abzeichen verdienen können. Außerdem wollte ich Statistiken einbauen, die auf dem Profil-Screen angezeigt werden, wie beispielsweise die höchste Punktzahl oder die Gesamtanzahl an gespielten Runden. Für die Ladebildschirme hatte ich geplant, Animationen mit After Effects zu erstellen. Hier hätte der Spieler beispielsweise ein UFO zum explodieren bringen können oder einen Teleporter-Knopf betätigen können. Dies hätte das „Alien“-Szenario spielerisch ergänzt.")

            HeadLine("Bugs")
            BodyText("Eigene Standorte aus dem Herausforderungsmodus werden nicht richtig geladen. Somit kann kein Spiel gestartet werden. Leider konnte ich nicht mehr herausfinden, woran das liegt. ")
        }

    }
}