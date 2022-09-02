package com.example.composefun2.feature.list_of_examples

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class ListOfExamplesState(val examples: List<String>)

class ListOfExamplesViewModel : ViewModel() {
    val uiState = mutableStateOf(
        ListOfExamplesState(
            examples = listOf(
                "Complex grid example",
                "Example 2",
                "Example 3"
            )
        )
    )
}
