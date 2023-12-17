package net.youapps.converter

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.youapps.converter.ui.screens.HomeScreen
import net.youapps.converter.ui.screens.audiotools.AudioEditorScreen
import net.youapps.converter.ui.screens.settings.AboutScreen
import net.youapps.converter.ui.screens.settings.SettingsScreen
import net.youapps.converter.ui.screens.videotools.VideoEditorScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Destination.Home.route
    ) {
        composable(route = Destination.Home.route) {
            HomeScreen(onNavigate = {
                navHostController.navigateTo(it.route)
            })
        }

        composable(route = Destination.AudioTool.route) {
            AudioEditorScreen()
        }

        composable(route = Destination.VideoTool.route) {
            VideoEditorScreen()
        }
        composable(route = Destination.About.route) {
            AboutScreen()
        }
        composable(route = Destination.Settings.route) {
            SettingsScreen {
                navHostController.navigateTo(Destination.About.route)
            }
        }
    }
}

fun NavHostController.navigateTo(route: String) = this.navigate(route) {
    launchSingleTop = true
    restoreState = true
}
