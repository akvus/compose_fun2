package com.example.composefun2.feature.list_of_examples

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class Example(val name: String, val path: String)

data class ListOfExamplesState(val examples: List<Example>)

class ListOfExamplesViewModel : ViewModel() {
    val uiState = mutableStateOf(
        ListOfExamplesState(
            examples = listOf(
                Example(name = "Complex grid", "complex_grid_page"),
                Example(name = "Player", "player_page")

            )
        )
    )
}
