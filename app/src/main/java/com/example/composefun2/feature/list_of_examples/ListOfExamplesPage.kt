package com.example.composefun2.feature.list_of_examples

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composefun2.LocalNavController
import com.example.composefun2.ui.theme.ComposeFun2Theme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfExamplesPage(viewMode: ListOfExamplesViewModel = viewModel()) {
    val uiState = viewMode.uiState

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Fun with Jetpack Compose") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            Body(uiState.value.examples)
        }
    }
}

@Composable
fun Body(examples: List<String>) {
    val navController = LocalNavController.current

    LazyColumn(contentPadding = PaddingValues(horizontal = 15.dp)) {
        itemsIndexed(
            examples,
            itemContent = { _, item ->
                Button(
                    colors = ButtonDefaults.textButtonColors(),
                    onClick = {
                        navController.navigate("example1")
                    },
                    modifier = Modifier.padding(6.dp)
                ) {
                    Text(item)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeFun2Theme {
    }
}
