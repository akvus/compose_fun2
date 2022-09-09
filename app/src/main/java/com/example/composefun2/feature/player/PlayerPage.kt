package com.example.composefun2.feature.player

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composefun2.LocalNavController
import com.example.composefun2.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerPage() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigation() }
    ) {
        Box(Modifier.padding(it)) {
            PageBody()
        }
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
private fun BottomNavigation() {
    BottomAppBar(
        containerColor = Color.Black
    ) {
        Row(
            Modifier.fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavIcon(icon = Icons.Outlined.Home, contentDescription = "Home") {
            }
            BottomNavIcon(icon = Icons.Outlined.Search, contentDescription = "Search") {
            }
            BottomNavIcon(icon = Icons.Outlined.List, contentDescription = "Library") {
            }
            BottomNavIcon(icon = Icons.Outlined.Star, contentDescription = "Hotlist") {
            }
        }
    }
}

@Composable
private fun BottomNavIcon(icon: ImageVector, contentDescription: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = contentDescription)
        Text(contentDescription, fontSize = 10.sp)
    }
}

@Composable
private fun PageBody() {
    Column {
        PlayListInfo()
        ActionButtons()
        SongsList()
        Spacer(Modifier.weight(1f))
        CurrentlyPlayedInfo()
    }
}

@Composable
private fun SongsList() {
    LazyColumn {
        item {
            MusicFileListItem()
        }
    }
}

@Composable
private fun ActionButtons() {
    Row {
        Button(onClick = { /*TODO*/ }) {
            Text("Play")
        }
        Button(onClick = { /*TODO*/ }) {
            Text("Shuffle")
        }
    }
}

@Composable
private fun PlayListInfo() {
    val image: Painter = painterResource(id = R.drawable.birds2)

    Row {
        Image(
            painter = image,
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Birds"
        )
        Column {
            Text("Album * 10 songs * 2022")
            Text("Charcoal")
            Text("Chopin")
            Row {
                Icon(Icons.Outlined.Share, contentDescription = "Share")
                Icon(Icons.Outlined.Share, contentDescription = "Share")
                Icon(Icons.Outlined.Share, contentDescription = "Share")
            }
        }
    }
}

@Composable
private fun MusicFileListItem() {
    Row {
        Text("Currently played")
    }
}

@Composable
fun CurrentlyPlayedInfo() {
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.Black).clip(RoundedCornerShape(10.dp))
    ) {
        Text("TODO")
        Text("Currently played")
    }
}
