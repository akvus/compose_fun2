package com.example.composefun2.feature.example_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composefun2.ui.theme.ComposeFun2Theme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamplesList(viewMode: ExampleListViewModel = viewModel()) {
    val uiState = viewMode.uiState

    ComposeFun2Theme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Fun with Jetpack Compose") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.LightGray
                    )
                )
            }
        ) {
            Body(uiState.value.examples)
        }
    }
}

@Composable
fun Body(examples: List<String>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(contentPadding = PaddingValues(horizontal = 15.dp)) {
            items(
                count = examples.size,
                itemContent = {
                    Button(
                        colors = ButtonDefaults.textButtonColors(),
                        onClick = {
                            // TODO
                        },
                        modifier = Modifier.padding(7.dp)
                    ) {
                        Text(" Examle")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeFun2Theme {
        Body(listOf("Example 1", "Example 2"))
    }
}
