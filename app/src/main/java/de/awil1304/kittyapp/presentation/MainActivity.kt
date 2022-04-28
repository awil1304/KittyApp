package de.awil1304.kittyapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.awil1304.kittyapp.presentation.kitty_list.KittyListScreen
import de.awil1304.kittyapp.presentation.ui.theme.KittyAppTheme
import de.awil1304.kittyapp.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KittyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    // app navigation
                    NavHost(
                        navController = navController,
                        startDestination = Screen.KittyListScreen.route
                    ) {

                        // composable block for each different screen
                        composable(
                            route = Screen.KittyListScreen.route
                        ) {
                            KittyListScreen(navController)
                        }
                    }

                }
            }
        }
    }
}
