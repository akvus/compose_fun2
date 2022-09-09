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

// Based on: https://lh3.googleusercontent.com/XRDT_NVtw5dh--ckNcyFDi46YycGfLqmuEEdxCeM6wviveYmfEkX7ReLWq53kRxdUDjba1ayE-JLGagHDSGmFgPjfvpW2-wYesOaTyw5C58ooxNeTOiigF5i38tSqoOk2-V5Av0t

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

    val DarkGray = Color(0xFF171C26)
    val MiddleGray = Color.Gray
    val LightGray = Color(0xFFDDDDDD)
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
        },
        actions = {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Search",
                modifier = Modifier.clickable { }.padding(16.dp)
            )
        }
    )
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
    Column(Modifier.fillMaxHeight()) {
        Box(
            Modifier.padding(
                start = PlayerTheme.padding2,
                end = PlayerTheme.padding3,
                top = PlayerTheme.padding1
            )
        ) {
            PlayListInfo()
        }
        Spacer(Modifier.height(PlayerTheme.padding2))
        ActionButtons(Modifier.padding(horizontal = PlayerTheme.padding3))
        Spacer(Modifier.height(PlayerTheme.padding2))
        SongsList(Modifier.weight(1f))
        CurrentlyPlayedInfo()
    }
}

@Composable
private fun SongsList(modifier: Modifier) {
    LazyColumn(modifier) {
        items(20) {
            MusicFileListItem(it + 1)
        }
    }
}

@Composable
private fun MusicFileListItem(index: Int) {
    Row(
        Modifier.clickable { }
            .padding(vertical = PlayerTheme.padding1, horizontal = PlayerTheme.padding3)
    ) {
        Text("%02d".format(index), Modifier.padding(vertical = PlayerTheme.padding1))
        Spacer(Modifier.width(PlayerTheme.padding3))
        Column {
            Text(
                "The sound of loudness",
                color = PlayerTheme.DarkGray,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Gravers ⬤ 3:55",
                color = PlayerTheme.MiddleGray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "More",
            tint = PlayerTheme.LightGray
        )
    }
}

@Composable
private fun ActionButtons(modifier: Modifier) {
    Row(modifier) {
        ActionButton(Icons.Outlined.PlayArrow, "Play", modifier = Modifier.weight(1f))
        Spacer(Modifier.width(PlayerTheme.padding2))
        ActionButton(
            Icons.Outlined.Edit,
            "Shuffle",
            color = Color(0xFFDDDDDD),
            textColor = PlayerTheme.DarkGray,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ActionButton(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = PlayerTheme.DarkGray,
    textColor: Color = Color.White
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier.height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = text, tint = textColor)
        Spacer(Modifier.width(PlayerTheme.padding1))
        Text(text, color = textColor)
    }
}

@Composable
private fun PlayListInfo() {
    val image: Painter = painterResource(id = R.drawable.birds2)
    val imageSize = 110.dp

    Row(
        Modifier.height(imageSize + PlayerTheme.padding2)
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
                .size(imageSize)
                .clip(RoundedCornerShape(PlayerTheme.padding2)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Birds"
        )
        Spacer(Modifier.width(PlayerTheme.padding3))
        Column(Modifier.padding(vertical = PlayerTheme.padding1)) {
            Text(
                "Album * 10 songs * 2022",
                fontSize = 12.sp,
                color = PlayerTheme.MiddleGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(2.dp))
            Text(
                "My best playlist has a long name",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Chopin",
                fontSize = 14.sp,
                color = PlayerTheme.MiddleGray,
                textDecoration = TextDecoration.Underline,
                maxLines = 1,
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
fun CurrentlyPlayedInfo() {
    Row(

        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .background(
                PlayerTheme.DarkGray,
                shape = RoundedCornerShape(
                    topStart = PlayerTheme.cornersRadius,
                    topEnd = PlayerTheme.cornersRadius
                )
            )
            .padding(PlayerTheme.padding1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.birds2),
            modifier = Modifier
                .padding(PlayerTheme.padding1)
                .size(50.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Birds"
        )
        Spacer(Modifier.width(PlayerTheme.padding3))
        Column {
            Text(
                "Nocturne 333",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = Color.White
            )
            Text(
                "Chopin",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = PlayerTheme.LightGray
            )
        }
        Spacer(Modifier.weight(1f))
        Icon(
            Icons.Outlined.FavoriteBorder,
            contentDescription = "Favourite",
            tint = Color.White,
            modifier = Modifier.size(30.dp).clickable { }
        )
        Spacer(Modifier.width(PlayerTheme.padding2))
        Icon(
            Icons.Outlined.Close,
            contentDescription = "Pause",
            tint = PlayerTheme.DarkGray,
            modifier = Modifier.size(30.dp).background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ).clickable { }
        )
        Spacer(Modifier.width(PlayerTheme.padding1))
    }
}

@Composable
private fun BottomNavigation() {
    BottomAppBar(containerColor = PlayerTheme.DarkGray) {
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
