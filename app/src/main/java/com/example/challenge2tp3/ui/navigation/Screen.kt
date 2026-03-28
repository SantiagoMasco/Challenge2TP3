package com.example.challenge2tp3.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String = "", val icon: ImageVector? = null) {
    object Home : Screen("home_screen", "Product", Icons.Default.Home)
    object Search : Screen("search_screen", "Search", Icons.Default.Search)
    object Cart : Screen("cart_screen", "Cart", Icons.Default.ShoppingCart)
    object Profile : Screen("profile_screen", "Profile", Icons.Default.Person)

    object Detail : Screen("detail_screen/{movieId}") {
        fun createRoute(movieId: Int) = "detail_screen/$movieId"
    }
}
