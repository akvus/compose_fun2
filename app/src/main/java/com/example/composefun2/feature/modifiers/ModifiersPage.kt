package com.example.composefun2.feature.modifiers

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModifiersPage() {
    Text("Some text", Modifier.padding(8.dp))
}
