package com.example.alien_abduction.domain.useCases

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//this use case contains all timer logic for the game
class TimerUseCase {

    private var timerJob: Job? = null

    fun startTimer(
        initialTime: Float,
        scope: CoroutineScope,
        onTick: (Float) -> Unit,
        onTimerFinished: () -> Unit
    ) {
        timerJob?.cancel()
        timerJob = scope.launch {
            var timeLeftSeconds = initialTime
            while (timeLeftSeconds > 0f) {
                delay(1000L)
                timeLeftSeconds -= 1f
                onTick(timeLeftSeconds.coerceAtLeast(0f))
                if (timeLeftSeconds <= 0f) {
                    onTimerFinished()
                    break
                }
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    fun resetTimer(
        initialTime: Float,
        scope: CoroutineScope,
        onTick: (Float) -> Unit,
        onTimerFinished: () -> Unit
    ) {
        stopTimer()
        onTick(initialTime)
        startTimer(initialTime, scope, onTick, onTimerFinished)
    }
}