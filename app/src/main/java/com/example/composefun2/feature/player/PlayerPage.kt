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
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composefun2.LocalNavController
import com.example.composefun2.R

// Based on: https://lh3.googleusercontent.com/XRDT_NVtw5dh--ckNcyFDi46YycGfLqmuEEdxCeM6wviveYmfEkX7ReLWq53kRxdUDjba1ayE-JLGagHDSGmFgPjfvpW2-wYesOaTyw5C58ooxNeTOiigF5i38tSqoOk2-V5Av0t

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun PlayerPage(
    playerPageViewModel: PlayerPageViewModel = hiltViewModel()
) {
    val uiState = playerPageViewModel.uiState.collectAsStateWithLifecycle()

    CompositionLocalProvider(LocalPlayerPageState provides uiState.value) {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigation() }
        ) {
            Box(Modifier.padding(it)) {
                PageBody()
            }
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
            .padding(PlayerPageTheme.padding2)
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
                start = PlayerPageTheme.padding2,
                end = PlayerPageTheme.padding3,
                top = PlayerPageTheme.padding1
            )
        ) {
            PlayListInfo()
        }
        Spacer(Modifier.height(PlayerPageTheme.padding2))
        ActionButtons(
            Modifier.padding(horizontal = PlayerPageTheme.padding3)
        )
        Spacer(Modifier.height(PlayerPageTheme.padding2))
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
            .padding(vertical = PlayerPageTheme.padding1, horizontal = PlayerPageTheme.padding3)
    ) {
        Text("%02d".format(index), Modifier.padding(vertical = PlayerPageTheme.padding1))
        Spacer(Modifier.width(PlayerPageTheme.padding3))
        Column {
            Text(
                "The sound of loudness",
                color = PlayerPageTheme.DarkGray,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Gravers ⬤ 3:55",
                color = PlayerPageTheme.MiddleGray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "More",
            tint = PlayerPageTheme.LightGray
        )
    }
}

@Composable
private fun ActionButtons(modifier: Modifier) {
    val viewModel: PlayerPageViewModel = hiltViewModel()
    val isPlaying = LocalPlayerPageState.current.isPlaying

    Row(modifier) {
        ActionButton(
            Icons.Outlined.PlayArrow,
            if (isPlaying) "Pause" else "Play",
            modifier = Modifier.weight(1f),
            onClick = viewModel::onPlayingChanged
        )
        Spacer(Modifier.width(PlayerPageTheme.padding2))
        ActionButton(
            Icons.Outlined.Edit,
            "Shuffle",
            color = Color(0xFFDDDDDD),
            textColor = PlayerPageTheme.DarkGray,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ActionButton(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = PlayerPageTheme.DarkGray,
    textColor: Color = Color.White,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = text, tint = textColor)
        Spacer(Modifier.width(PlayerPageTheme.padding1))
        Text(text, color = textColor)
    }
}

@Composable
private fun PlayListInfo() {
    val image: Painter = painterResource(id = R.drawable.birds2)
    val imageSize = 110.dp

    Row(
        Modifier.height(imageSize + PlayerPageTheme.padding2)
    ) {
        // TODO: this image should be aligned to padding on left, was now moved because of adding shadow - fix
        Image(
            painter = image,
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(PlayerPageTheme.padding2),
                    clip = false
                )
                .padding(PlayerPageTheme.padding1)
                .size(imageSize)
                .clip(RoundedCornerShape(PlayerPageTheme.padding2)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Birds"
        )
        Spacer(Modifier.width(PlayerPageTheme.padding3))
        Column(Modifier.padding(vertical = PlayerPageTheme.padding1)) {
            Text(
                "Album * 10 songs * 2022",
                fontSize = 12.sp,
                color = PlayerPageTheme.MiddleGray,
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
                color = PlayerPageTheme.MiddleGray,
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
                PlayerPageTheme.DarkGray,
                shape = RoundedCornerShape(
                    topStart = PlayerPageTheme.cornersRadius,
                    topEnd = PlayerPageTheme.cornersRadius
                )
            )
            .padding(PlayerPageTheme.padding1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.birds2),
            modifier = Modifier
                .padding(PlayerPageTheme.padding1)
                .size(50.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Birds"
        )
        Spacer(Modifier.width(PlayerPageTheme.padding3))
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
                color = PlayerPageTheme.LightGray
            )
        }
        Spacer(Modifier.weight(1f))
        Icon(
            Icons.Outlined.FavoriteBorder,
            contentDescription = "Favourite",
            tint = Color.White,
            modifier = Modifier.size(30.dp).clickable { }
        )
        Spacer(Modifier.width(PlayerPageTheme.padding2))
        Icon(
            Icons.Outlined.Close,
            contentDescription = "Pause",
            tint = PlayerPageTheme.DarkGray,
            modifier = Modifier.size(30.dp).background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ).clickable { }
        )
        Spacer(Modifier.width(PlayerPageTheme.padding1))
    }
}

@Composable
private fun BottomNavigation() {
    BottomAppBar(containerColor = PlayerPageTheme.DarkGray) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(
                        topStart = PlayerPageTheme.cornersRadius,
                        topEnd = PlayerPageTheme.cornersRadius
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
