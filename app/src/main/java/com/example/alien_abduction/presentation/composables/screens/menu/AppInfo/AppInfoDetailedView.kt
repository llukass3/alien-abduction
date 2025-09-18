package com.example.alien_abduction.presentation.composables.screens.menu.AppInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alien_abduction.ui.theme.AlienabductionTheme

@Composable
fun AppInfoDetailedView(
    screenId: Int,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 25.dp)
    ) {
        if(screenId == 1)
            AppDescription(modifier = Modifier.matchParentSize())

        if(screenId == 2)
            UXDescription(modifier = Modifier.matchParentSize())
    }
}

@Composable
fun HeadLine(text: String) {
    Text(
        modifier = Modifier.padding(bottom = 15.dp, top = 30.dp),
        color = Color.Black,
        style = MaterialTheme.typography.headlineLarge,
        fontSize = 23.sp,
        textAlign = TextAlign.Justify,
        text = text
    )
}

@Composable
fun BodyText(text: String) {
    Text(
        modifier = Modifier.padding(bottom = 5.dp),
        color = Color.Black,
        textAlign = TextAlign.Justify,
        text = text
    )
}




@Preview
@Composable
fun AppInfoDetailedViewPreview() {
    AlienabductionTheme {
        Scaffold { innerPadding ->
            AppInfoDetailedView(1,modifier = Modifier.padding(innerPadding))
        }
    }
}