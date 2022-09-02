package com.example.composefun2.feature.example1

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composefun2.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Example1() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Example 1") },

                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray
                ),
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
    ) {
        Surface(modifier = Modifier.padding(it)) {
            Text("Text")
        }
    }
}
