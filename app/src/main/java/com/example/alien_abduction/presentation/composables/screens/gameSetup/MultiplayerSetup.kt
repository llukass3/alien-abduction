package com.example.alien_abduction.presentation.composables.screens.gameSetup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alien_abduction.R
import com.example.alien_abduction.domain.GameMode
import com.example.alien_abduction.domain.dataModels.Player
import com.example.alien_abduction.presentation.composables.customComposables.CircularButton
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModel
import com.example.alien_abduction.presentation.viewModels.GameSetupViewModelFactory
import com.example.alien_abduction.ui.theme.AlienDarkGray
import com.example.alien_abduction.ui.theme.AlienabductionTheme
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun MultiplayerGameSetup(
    modifier: Modifier = Modifier,
    viewModel: GameSetupViewModel
) {
    val players by viewModel.players.collectAsState()

    // State to track which player is being edited (null = none)
    var editingPlayerIndex by remember { mutableStateOf<Int?>(null) }
    var editingName by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        PlayerList(
            modifier = Modifier.padding(bottom = 20.dp),
            playerList = players,
            editingPlayerIndex = editingPlayerIndex,
            editingName = editingName,
            onStartEdit = { index, currentName ->
                editingPlayerIndex = index
                editingName = currentName
            },
            onNameChange = { newName ->
                // Only change if not blank or empty
                if (newName.isNotBlank() && editingPlayerIndex != null) {
                    viewModel.changePlayerName(editingPlayerIndex!!, newName)
                }
                editingPlayerIndex = null
                editingName = ""
                focusManager.clearFocus()
            },
            onEditingNameChange = { editingName = it }
        )
        if (players.size > 1)
            CircularButton(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .size(40.dp),
                text = "-",
                onClick = {viewModel.removePlayer()}
            )

        if (players.size < 4)
            CircularButton(
                modifier = Modifier
                    .size(40.dp),
                text = "+",
                onClick = {viewModel.addPlayer()}
            )
    }
}

@Composable
fun PlayerList(
    modifier: Modifier = Modifier,
    playerList: List<Player>,
    editingPlayerIndex: Int?,
    editingName: String,
    onStartEdit: (index: Int, currentName: String) -> Unit,
    onNameChange: (newName: String) -> Unit,
    onEditingNameChange: (String) -> Unit,
) {
    Column(
        modifier = modifier.padding(top = 20.dp),
    ) {
        playerList.forEachIndexed { index, player ->
            PlayerListEntry(
                player = player,
                isEditing = (index == editingPlayerIndex),
                editingName = editingName,
                onStartEdit = { onStartEdit(index, player.nickname) },
                onNameChange = onNameChange,
                onEditingNameChange = onEditingNameChange
            )
            if (player != playerList.last())
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = AlienDarkGray,
                    thickness = 1.5.dp
                )
        }
    }
}

@Composable
fun PlayerListEntry(
    player: Player,
    isEditing: Boolean = false,
    editingName: String = "",
    onStartEdit: () -> Unit = {},
    onNameChange: (String) -> Unit = {},
    onEditingNameChange: (String) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(end = 7.dp)
                .size(9.dp)
                .background(
                    shape = CircleShape,
                    color = player.slot.color
                )
        )
        Text(
            modifier = Modifier.padding(start = 3.dp),
            text = player.slot.position,
            textAlign = TextAlign.End,
            fontSize = 20.sp
        )

        if (isEditing) {
            TextField(
                value = editingName,
                onValueChange = onEditingNameChange,
                singleLine = true,
                textStyle = MaterialTheme.typography.titleMedium,
                colors = TextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onNameChange(editingName)
                        focusManager.clearFocus()
                    }
                ),
                modifier = Modifier
                    .padding(start = 25.dp)
                    .weight(1f)
            )
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp)
                    .clickable { onNameChange(editingName) }, // Confirm editing
                painter = painterResource(R.drawable.pen),
                contentDescription = "save name",
            )
        } else {
            Text(
                modifier = Modifier
                    .padding(start = 25.dp)
                    .clickable { onStartEdit() },
                text = player.nickname,
                style = MaterialTheme.typography.titleMedium,
            )
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 5.dp)
                    .clickable { onStartEdit() },
                painter = painterResource(R.drawable.pen),
                contentDescription = "edit profile",
            )
        }
    }
}

@Preview
@Composable
fun MultiplayerSetupPreview() {
    AlienabductionTheme {
        val mode = GameMode.MULTIPLAYER
        Scaffold { innerPadding ->
            GameSetupScreen(
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel<GameSetupViewModel>(
                    factory = GameSetupViewModelFactory(mode)
                )
            )
        }
    }
}