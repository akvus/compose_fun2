package com.example.composefun2.feature.complex_grid_example

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composefun2.LocalNavController
import com.example.composefun2.R

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
        val image: Painter = painterResource(id = R.drawable.birds)

        Surface(modifier = Modifier.padding(paddingValues)) {
            Column {
                Text("Text", Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow {
                    items(count = 10) {
                        Card(
                            modifier = Modifier
                                .size(height = 120.dp, width = 150.dp)
                                .padding(8.dp)
                                .background(
                                    MaterialTheme.colorScheme.background,
                                    RoundedCornerShape(15.dp)
                                )
                        ) {
                            Image(
                                painter = image,
                                contentScale = ContentScale.FillBounds,
                                contentDescription = "Birds"
                            )
                        }
                    }
                }
            }
        }
    }
}
