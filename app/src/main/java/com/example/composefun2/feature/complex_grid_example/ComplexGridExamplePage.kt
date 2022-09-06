package com.example.composefun2.feature.complex_grid_example
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
*  - clean the code
*  - bottom navigation
*  - circular FAB
*  - Nested LazyColumns
*  - Paddings
*  - Theming?
*  - make fake error and use LaunchedEffect to show one time error message with snack bar
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplexGridExamplePage() {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FAB(scope, snackBarHostState)
        },
        topBar = {
            AppBar()
        }
    ) { paddingValues ->
        ComplexGridExampleBody(paddingValues)
    }
}

@Composable
private fun ComplexGridExampleBody(paddingValues: PaddingValues) {
    Surface(modifier = Modifier.padding(paddingValues)) {
        Column(
            Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp)
                .fillMaxWidth()
        ) {
            Text("Text", Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            BirdsSpinner()
            Spacer(modifier = Modifier.height(16.dp))
            ElementsFilters()
            Spacer(modifier = Modifier.height(16.dp))
            StaggeredBirds()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun FAB(
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
private fun StaggeredBirds() {
    val image: Painter = painterResource(id = R.drawable.birds2)

    LazyStaggeredGrid(
        columnCount = 2
    ) {
        for (i in 0 until 10000) {
            item {
                BirdCard(
                    image,
                    modifier = Modifier
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
                    .padding(start = 16.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(15.dp)
                    )
            )
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
            .padding(start = 16.dp)
            .border(BorderStroke(1.dp, Color(0xFFAAAAAA)), shape = RoundedCornerShape(15.dp))
            .background(color = Color.White),
        colors = CardDefaults.cardColors(containerColor = if (filter.active) Color(0xFFEEEEEE) else Color.White)
    ) {
        Box(modifier = Modifier.padding(horizontal = 16.dp), contentAlignment = Alignment.Center) {
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
