package com.example.composefun2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composefun2.feature.example1.Example1
import com.example.composefun2.feature.example_list.ExamplesList
import com.example.composefun2.ui.theme.ComposeFun2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeFun2Theme {
                NavHost(navController = navController, startDestination = "example_list") {
                    composable("example_list") { ExamplesList(navController) }
                    composable("example1") { Example1(navController) }
                }
            }
        }
    }
}
