package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.layouts.CharacterDetailScreen
import com.example.myapplication.layouts.CharacterScreen
import com.example.myapplication.routing.Screen
import com.example.myapplication.ui.RickMortyTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RickMortyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.CharacterScreen.route
                ) {
                    composable(Screen.CharacterScreen.route) {
                        CharacterScreen(
                            Modifier.fillMaxSize(),
                            onCharSelect = { id ->
                                navController.navigate(
                                    Screen.CharacterDetailScreen.routeWithoutArgs + "$id",
                                )
                            })
                    }
                    composable(
                        Screen.CharacterDetailScreen.route,
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { navBackStackEntry ->
                        CharacterDetailScreen(navBackStackEntry.arguments?.getInt("id")!!)
                    }
                }
            }

        }
    }
}