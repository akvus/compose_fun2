package com.example.composefun2.feature.list_of_examples

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composefun2.LocalNavController
import com.example.composefun2.R
import com.example.composefun2.ui.theme.ComposeFun2Theme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfExamplesPage(viewMode: ListOfExamplesViewModel = viewModel()) {
    val uiState = viewMode.uiState

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Fun with Jetpack Compose") }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            PageBody(uiState.value.examples)
        }
    }
}

@Composable
private fun PageBody(examples: List<Example>) {
    LazyColumn {
        itemsIndexed(
            examples,
            itemContent = { _, example ->
                ExampleListItem(example)
            }
        )
    }
}

@Composable
private fun ExampleListItem(
    example: Example
) {
    val navController = LocalNavController.current

    Row(
        Modifier
            .clickable {
                navController.navigate(example.path)
            }
            .padding(horizontal = 30.dp, vertical = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.clouds),
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Clouds"
        )
        Spacer(Modifier.width(16.dp))
        Text(
            example.name,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val list = buildList {
        for (i in 0..100)
            add(
                Example("Example", "some_path")
            )
    }

    ComposeFun2Theme {
        PageBody(examples = list)
    }
}
