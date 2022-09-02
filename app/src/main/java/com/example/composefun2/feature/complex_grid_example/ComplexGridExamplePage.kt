package com.example.composefun2.feature.complex_grid_example

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composefun2.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplexGridExamplePage() {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Discover", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                navigationIcon = {
                    val navController = LocalNavController.current
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Text("Text", Modifier.padding(horizontal = 16.dp))
        }
    }
}
