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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
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

// TODO temporary solution, should use LocalComposition and support dark mode
private object PlayerTheme {
    val cornersRadius = 10.dp
    val padding1 = 8.dp
    val padding2 = 16.dp
    val padding3 = 24.dp
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
            Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(
                        topStart = PlayerTheme.cornersRadius,
                        topEnd = PlayerTheme.cornersRadius
                    )
                ),
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
            .padding(PlayerTheme.padding2)
    ) {
        Icon(icon, contentDescription = contentDescription)
        Text(contentDescription, fontSize = 10.sp)
    }
}

@Composable
private fun PageBody() {
    Column {
        Column(
            modifier = Modifier.padding(PlayerTheme.padding3)
        ) {
            PlayListInfo()
            Spacer(Modifier.height(PlayerTheme.padding2))
            ActionButtons()
            Spacer(Modifier.height(PlayerTheme.padding2))
            SongsList()
        }

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

    Row(
        Modifier.height(120.dp + PlayerTheme.padding2)
    ) {
        // TODO: this image should be aligned to padding on left, was now moved because of adding shadow - fix
        Image(
            painter = image,
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(PlayerTheme.padding2),
                    clip = false
                )
                .padding(PlayerTheme.padding1)
                .size(120.dp)
                .clip(RoundedCornerShape(PlayerTheme.padding2)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Birds"
        )
        Spacer(Modifier.width(PlayerTheme.padding3))
        Column(Modifier.padding(vertical = PlayerTheme.padding1)) {
            Text(
                "Album * 10 songs * 2022",
                fontSize = 12.sp,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                "My best playlist has a long name",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Chopin",
                fontSize = 14.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.Underline,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.weight(1f))
            Row {
                val modifier = Modifier.size(20.dp)

                Icon(Icons.Outlined.Place, contentDescription = "Play", modifier)
                Spacer(Modifier.width(16.dp))
                Icon(Icons.Outlined.Share, contentDescription = "Share", modifier)
                Spacer(Modifier.width(16.dp))
                Icon(Icons.Outlined.Refresh, contentDescription = "Refresh", modifier)
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
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.Black,
                shape = RoundedCornerShape(
                    topStart = PlayerTheme.cornersRadius,
                    topEnd = PlayerTheme.cornersRadius
                )
            )
    ) {
        Text("TODO")
        Text("Currently played")
    }
}