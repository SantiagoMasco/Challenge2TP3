package com.example.challenge2tp3.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.challenge2tp3.ui.screens.detail.DetailScreen
import com.example.challenge2tp3.ui.screens.favorites.FavoritesScreen
import com.example.challenge2tp3.ui.screens.home.HomeScreen
import com.example.challenge2tp3.ui.screens.settings.SettingsScreen
import com.example.challenge2tp3.ui.screens.profile.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(onProductClick = { productId ->
                navController.navigate(Screen.Detail.createRoute(productId))
            })
        }
        composable(route = Screen.Search.route) {
            Text(text = "Search Screen")
        }
        composable(route = Screen.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen()
        }
        composable(route = Screen.Cart.route) {
            Text(text = "Cart Screen")
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("movieId") {
                type = androidx.navigation.NavType.IntType
            })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("movieId")
            DetailScreen(
                productId = productId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
