package com.example.composefun2.feature.complex_grid
// Layout from:
// https://dribbble.com/shots/15348694-Photo-Editing-App-Exploration/attachments/7108950?mode=media

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composefun2.LocalNavController
import com.example.composefun2.R
import com.example.composefun2.shared.composable.LazyStaggeredGrid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
* TODO
*  - bottom navigation
*  - find a different work around to scroll Grid together with header (effects)?
*/

private const val headerHeight = 270f
private val padding = 16.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplexGridExamplePage() {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val someFakeStateValue = true

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            Fab(scope, snackbarHostState)
        },
        topBar = {
            AppBar()
        }
    ) { paddingValues ->
        if (someFakeStateValue) {
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(
                    message = "Error message",
                    actionLabel = "Retry message"
                )
            }
        }

        ComplexGridExampleBody(paddingValues)
    }
}

@Composable
private fun ComplexGridExampleBody(paddingValues: PaddingValues) {
    val scrollState: MutableState<Float> = remember { mutableStateOf(0f) }

    Surface(
        modifier = Modifier.padding(paddingValues)
    ) {
        Column(
            Modifier.absoluteOffset(y = scrollState.value.dp)
        ) {
            Text("Text", Modifier.padding(horizontal = padding))
            Spacer(modifier = Modifier.height(padding))
            BirdsSpinner()
            Spacer(modifier = Modifier.height(padding))
            ElementsFilters()
        }
        Box(
            modifier = Modifier.padding(
                top = (headerHeight + scrollState.value).dp,
                start = 8.dp,
                end = 8.dp
            )
        ) {
            StaggeredBirds(
                onFirstItemPositioned = { offset ->
                    val y = offset.y
                    scrollState.value =
                        if (y < -headerHeight) -headerHeight else if (y > 0) 0f else y
                },
                isScrollEnabled = {
                    true
                }
            )
        }

//        Box(
//            modifier = Modifier.fillMaxSize()
//                .draggable(
//                    orientation = Orientation.Vertical,
//                    state = rememberDraggableState { delta ->
//                        offset += delta
//                        val h = scrollState.value + delta
//                        scrollState.value =
//                            if (h < -headerHeight) -headerHeight else if (h > 0) 0f else h
//                    }
//                )
//        ) {}
    }
}

@Composable
private fun Fab(
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState
) {
    FloatingActionButton(
        onClick = {
            scope.launch {
                snackBarHostState.showSnackbar("Some snackbar")
            }
        },
        containerColor = Color(0xFF36382E),
        contentColor = Color(0xFFEDE6E3)
    ) {
        Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
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

@Composable
private fun StaggeredBirds(
    onFirstItemPositioned: (offset: Offset) -> Unit,
    isScrollEnabled: () -> Boolean
) {
    val image: Painter = painterResource(id = R.drawable.birds2)

    LazyStaggeredGrid(columnCount = 2, isScrollEnabled = isScrollEnabled) {
        for (i in 0 until 10000) {
            val modifier = if (i == 0) Modifier
                .onGloballyPositioned { c ->
                    if (i == 0) {
                        onFirstItemPositioned(c.positionInParent())
                    }
                }
            else Modifier

            item {
                BirdCard(
                    image,
                    modifier = modifier
                        .height((200 + 50 * (i % 3 + 1)).dp)
                        .padding(4.dp)
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(15.dp)
                        )
                )
            }
        }
    }
}

@Composable
private fun BirdsSpinner() {
    val image: Painter = painterResource(id = R.drawable.birds)

    LazyRow {
        items(count = 10) {
            BirdCard(
                image,
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = padding)
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(15.dp)
                    )
            )
        }
        item {
            Spacer(modifier = Modifier.width(padding))
        }
    }
}

@Composable
private fun BirdCard(image: Painter, modifier: Modifier) {
    Card(
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = image,
                contentScale = ContentScale.FillBounds,
                contentDescription = "Birds"
            )
            Icon(
                Icons.Outlined.Send,
                contentDescription = "Star",
                tint = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun ElementsFilters() {
    val filters = listOf(
        Filter("Some filter", Icons.Filled.Refresh, Color(0xFFF06449)),
        Filter("Other filter that is too long", Icons.Filled.Delete, Color(0xFF5BC3EB), true),
        Filter("Some filter", Icons.Filled.Refresh, Color(0xFFF06449)),
        Filter("Some filter", Icons.Filled.Refresh, Color(0xFFF06449)),
        Filter("Other filter", Icons.Filled.Delete, Color(0xFF5BC3EB)),
        Filter("Other filter", Icons.Filled.Delete, Color(0xFF5BC3EB))
    )

    LazyRow {
        itemsIndexed(filters) { _, filter ->
            ElementsFilter(filter)
        }
        item {
            Spacer(Modifier.width(padding))
        }
    }
}

data class Filter(
    val text: String,
    val icon: ImageVector,
    val color: Color,
    val active: Boolean = false
)

@Composable
private fun ElementsFilter(filter: Filter) {
    Card(
        modifier = Modifier
            .height(50.dp)
            .padding(start = padding)
            .border(BorderStroke(1.dp, Color(0xFFAAAAAA)), shape = RoundedCornerShape(15.dp))
            .background(color = Color.White),
        colors = CardDefaults.cardColors(containerColor = if (filter.active) Color(0xFFEEEEEE) else Color.White)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = padding),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier.fillMaxHeight()) {
                Icon(
                    filter.icon,
                    contentDescription = "Icon",
                    tint = filter.color,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)

                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Text(filter.text, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}
