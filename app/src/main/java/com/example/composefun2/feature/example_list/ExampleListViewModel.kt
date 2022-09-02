package com.example.composefun2.feature.example_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class ExampleListState(val examples: List<String>)

class ExampleListViewModel : ViewModel() {
    val uiState = mutableStateOf(
        ExampleListState(
            examples = listOf(
                "Example 1",
                "Example 2"
            )
        )
    )
}
