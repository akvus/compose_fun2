package com.example.composefun2.feature.content_with_chart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composefun2.LocalNavController

// Based on https://dribbble.com/shots/9691493-UI-Design-Beautiful-Weather-App-Experience-Concept

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentWithChartPage() {
    Scaffold(
        topBar = {
            TopBar()
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            PageBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    LargeTopAppBar(
        title = {
            Text("Weather app")
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
                Icons.Outlined.MailOutline,
                contentDescription = "Search",
                modifier = Modifier.clickable { }.padding(16.dp)
            )
        }
    )
}

@Composable
private fun PageBody() {
    Column {
        LazyColumn(Modifier.weight(1f)) {
            item {
                PageHeader()
                Chart()
                PageContent()
            }
        }
        Footer()
    }
}

@Composable
private fun PageHeader() {
    Text("TODO")
}

@Composable
private fun Chart() {
}

@Composable
private fun PageContent() {
    Text("TODO")
}

@Composable
private fun Footer() {
    Text("TODO")
}
