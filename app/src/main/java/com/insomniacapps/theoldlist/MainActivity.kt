package com.insomniacapps.theoldlist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.insomniacapps.theoldlist.settings.ui.WallpaperSettingsUi
import com.insomniacapps.theoldlist.ui.TasksScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    TasksScreen(navController)
                }
                composable("settings") {
                    WallpaperSettingsUi(navController = navController)
                }
            }
        }
    }
}