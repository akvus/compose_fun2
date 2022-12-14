package com.example.composefun2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composefun2.feature.complex_grid.ComplexGridExamplePage
import com.example.composefun2.feature.content_with_chart.ContentWithChartPage
import com.example.composefun2.feature.list_of_examples.ListOfExamplesPage
import com.example.composefun2.feature.modifiers.ModifiersPage
import com.example.composefun2.feature.player.PlayerPage
import com.example.composefun2.ui.theme.ComposeFun2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ComposeFun2Theme {
                CompositionLocalProvider(LocalNavController provides navController) {
                    NavHost(navController = navController, startDestination = "example_list") {
                        composable("example_list") { ListOfExamplesPage() }
                        composable("complex_grid_page") { ComplexGridExamplePage() }
                        composable("player_page") { PlayerPage() }
                        composable("weather_page") { ContentWithChartPage() }
                        composable("modifiers_page") { ModifiersPage() }
                    }
                }
            }
        }
    }
}
