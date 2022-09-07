package com.example.composefun2.feature.player

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composefun2.LocalNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerPage() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) {
        Text("Test")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    SmallTopAppBar(
        title = {
            Text("Player")
        },
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

@Composable
private fun BottomBar() {
    BottomAppBar {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            BottomNavIcon(icon = Icons.Outlined.Home, contentDescription = "Home")
            BottomNavIcon(icon = Icons.Outlined.Home, contentDescription = "Home")
            BottomNavIcon(icon = Icons.Outlined.Home, contentDescription = "Home")
            BottomNavIcon(icon = Icons.Outlined.Home, contentDescription = "Home")
            BottomNavIcon(icon = Icons.Outlined.Home, contentDescription = "Home5")
        }
    }
}

@Composable
private fun BottomNavIcon(icon: ImageVector, contentDescription: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = contentDescription)
        Text(contentDescription)
    }
}
