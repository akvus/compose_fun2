package com.example.composefun2.feature.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composefun2.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class PlayerPageState(val isPlaying: Boolean = false)

@HiltViewModel
class PlayerPageViewModel @Inject constructor() : ViewModel() {
    private val isPlayingState = MutableStateFlow(false)

    val uiState: StateFlow<PlayerPageState> = isPlayingState.map { PlayerPageState(it) }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = PlayerPageState()
    )

    fun onPlayingChanged() {
        isPlayingState.value = !isPlayingState.value
    }
}
