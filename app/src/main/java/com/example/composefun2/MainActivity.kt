package com.example.composefun2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composefun2.ui.theme.ComposeFun2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFun2Theme {
                Scaffold(
                    topBar = {
                        SmallTopAppBar(
                            title = { Text("Fun with Jetpack Compose") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color.LightGray
                            )
                        )
                    }
                ) {
                    Body()
                }
            }
        }
    }
}

@Composable
fun Body() {
    Surface(
        modifier = Modifier.fillMaxSize().padding(
            16.dp
        ),
        color = MaterialTheme.colorScheme.background
    ) {
        ListOfExamples()
    }
}

@Composable
fun ListOfExamples() {
    LazyColumn {
        items(
            count = 100,
            itemContent = {
                Text("Example")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeFun2Theme {
    }
}
