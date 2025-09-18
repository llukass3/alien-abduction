package com.example.alien_abduction.presentation.composables.screens.menu.AppInfo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UXDescription(modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier) {
        item {
            HeadLine("Benutzung und UX")
            BodyText("Um wichtigen UX Prinzipien zu folgen habe ich darauf geachtet, in der App ein einheitliches Theming zu verwenden. Hierzu habe ich die von Android Studio bereitgesptellten Theming-Funktionen genutzt und beispielsweise ein eigenes Farbschema und eigene Typografie durch Einbindung von Google Fonts implementiert. Alle Icons und Bilder habe ich selbst mit Adobe Illustrator entworfen und exportiert. Der User erhält für jeden Klick ein Feedback. Durch sogenannte „Toasts“ (kurze Texteinblendungen) wird mitgeteilt, dass bestimmte Aktionen erfolgt sind (z.B. „Standort Ausgewählt) oder es werden Anweisungen gegeben (z.B. „Bitte wähle einen Standort aus“), wenn der Spieler das Spiel beenden möchte, ohne dass ein Standort ausgewählt wurde. ")
            BodyText("Nicht nur textliches, sondern auch Visuelles Feedback informiert den User über die Geschehnisse in der App. Als Beispiel der „Guess“ Knopf in der Spiel-Ansicht: Ist kein Standort ausgewählt, ist er Grün-Schwarz gefärbt. Ist ein Standort ausgewählt, färbt er sich Weiß-Grün, was dem User signalisiert, dass beim nächsten Klick eine andere Aktion erfolgen wird (in diesem Fall wird das Spiel beendet).")
            BodyText("Die Type-Safe-Navigation Bibliothek von Android ermöglicht ein nahtloses wechseln zwischen den Bildschirmen mit animierten Übergängen und stellt einen zwischengespeicherten Back Stack bereit, der zuvor besuchte Screens speichert und durch das betätigen des „zurück“ Buttons von Android wieder aufruft.")
        }
    }
}