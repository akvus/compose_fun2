package com.example.composefun2.feature.complex_grid_example

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
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

        Surface(modifier = Modifier.padding(paddingValues)) {
            Column {
                Text("Text", Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                BirdsSpinner()
                Spacer(modifier = Modifier.height(16.dp))
                ElementsFilters()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BirdsSpinner() {
    val image: Painter = painterResource(id = R.drawable.birds)

    LazyRow {
        items(count = 10) {
            BirdCard(image)
        }
    }
}

@Composable
fun BirdCard(image: Painter) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(start = 16.dp)
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(15.dp)
            )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = image,
                contentScale = ContentScale.FillBounds,
                contentDescription = "Birds"
            )
            Icon(
                Icons.Outlined.Star,
                contentDescription = "Star",
                tint = Color.White,
                modifier = Modifier.padding(8.dp).align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun ElementsFilters() {
    Row {
        ElementsFilter("Some filter", Icons.Filled.Refresh, Color.Green)
        ElementsFilter("Other filter", Icons.Filled.Delete, Color.Red)
        ElementsFilter("Some filter", Icons.Filled.Refresh, Color.Green)
        ElementsFilter("Some filter", Icons.Filled.Refresh, Color.Green)
        ElementsFilter("Other filter", Icons.Filled.Delete, Color.Red)
        ElementsFilter("Other filter", Icons.Filled.Delete, Color.Red)
    }
}

@Composable
fun ElementsFilter(text: String, icon: ImageVector, color: Color) {
    Card(
        modifier = Modifier
            .height(50.dp)
            .padding(start = 16.dp)
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(15.dp)
            )
    ) {
        Row {
            Icon(
                icon,
                contentDescription = "Icon",
                tint = color,
                modifier = Modifier.padding(8.dp)
            )
            Text(text)
        }
    }
}
