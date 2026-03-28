package com.example.challenge2tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.challenge2tp3.ui.navigation.NavGraph
import com.example.challenge2tp3.ui.navigation.Screen
import com.example.challenge2tp3.ui.theme.BackgroundBeige
import com.example.challenge2tp3.ui.theme.Challenge2TP3Theme
import com.example.challenge2tp3.ui.theme.PrimaryBrown
import com.example.challenge2tp3.ui.theme.SurfaceWhite

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    Challenge2TP3Theme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val items = listOf(
            Screen.Home,
            Screen.Search,
            Screen.Cart,
            Screen.Profile
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("TITLE", color = Color.DarkGray) },
                    navigationIcon = {
                        IconButton(onClick = { /* TODO: Open drawer */ }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: Profile */ }) {
                            Icon(
                                Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    modifier = Modifier.height(64.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = SurfaceWhite
                    )
                )
            },
            bottomBar = {
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.height(100.dp)
                ) {
                    NavigationBar(
                        containerColor = Color.White,
                        tonalElevation = 8.dp,
                        modifier = Modifier.height(80.dp)
                    ) {
                        items.forEachIndexed { index, screen ->
                            if (index == 2) {
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { },
                                    icon = { Box(Modifier.size(24.dp)) },
                                    enabled = false
                                )
                            }

                            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

                            NavigationBarItem(
                                icon = {
                                    screen.icon?.let {
                                        Icon(it, contentDescription = screen.title)
                                    }
                                },
                                label = { Text(screen.title) },
                                selected = isSelected,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = PrimaryBrown,
                                    selectedTextColor = PrimaryBrown,
                                    unselectedIconColor = Color.Gray,
                                    unselectedTextColor = Color.Gray,
                                    indicatorColor = Color.Transparent
                                )
                            )
                        }
                    }

                    FloatingActionButton(
                        onClick = { /* TODO: Main Action */ },
                        shape = CircleShape,
                        containerColor = PrimaryBrown,
                        contentColor = Color.White,
                        modifier = Modifier
                            .offset(y = (-30).dp)
                            .size(64.dp)
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Shop",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(BackgroundBeige)
            ) {
                NavGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    MainApp()
}
