package com.example.challenge2tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.challenge2tp3.ui.navigation.NavGraph
import com.example.challenge2tp3.ui.navigation.Screen
import com.example.challenge2tp3.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MainApp() }
    }
}

/**
 * SHAPE DEFINITIVO: Con notch ampliado para efecto "floating".
 */
class FigmaMountainNotchShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height
            val cutoutRadius = with(density) { 46.dp.toPx() } 
            val sideHeight = with(density) { 65.dp.toPx() }   
            val peakRise = with(density) { 15.dp.toPx() }     
            val baseLineY = height - sideHeight
            val peakY = baseLineY - peakRise
            
            moveTo(0f, height)
            lineTo(0f, baseLineY)
            cubicTo(width * 0.15f, baseLineY, width * 0.35f, peakY, width * 0.5f - cutoutRadius, peakY)
            arcTo(
                rect = Rect(
                    left = width / 2 - cutoutRadius,
                    top = peakY - cutoutRadius,
                    right = width / 2 + cutoutRadius,
                    bottom = peakY + cutoutRadius
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )
            cubicTo(width * 0.5f + cutoutRadius, peakY, width * 0.85f, peakY, width, baseLineY)
            lineTo(width, height)
            close()
        }
        return Outline.Generic(path)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    Challenge2TP3Theme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val drawerItems = listOf(
            NavigationItem("Product", Screen.Home.route, Icons.Filled.Home, Icons.Outlined.Home),
            NavigationItem("Search", Screen.Search.route, Icons.Filled.Search, Icons.Outlined.Search),
            NavigationItem("Favorites", Screen.Favorites.route, Icons.Filled.Favorite, Icons.Outlined.Favorite),
            NavigationItem("Settings", Screen.Settings.route, Icons.Filled.Settings, Icons.Outlined.Settings),
            NavigationItem("Profile", Screen.Profile.route, Icons.Filled.Person, Icons.Outlined.Person)
        )

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = SurfaceWhite,
                    drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                ) {
                    Spacer(Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = PrimaryBrown
                            )
                            Spacer(Modifier.height(12.dp))
                            Text("Martin", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text("UI UX DESIGN", fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                    HorizontalDivider(Modifier.padding(horizontal = 24.dp), color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(Modifier.height(16.dp))
                    drawerItems.forEach { item ->
                        val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                        NavigationDrawerItem(
                            label = { Text(item.label) },
                            selected = isSelected,
                            onClick = {
                                scope.launch { drawerState.close() }
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(if (isSelected) item.selectedIcon else item.unselectedIcon, contentDescription = null) },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = PrimaryBrown.copy(alpha = 0.1f),
                                selectedIconColor = PrimaryBrown,
                                selectedTextColor = PrimaryBrown,
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            }
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = BackgroundBeige, 
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("TITLE", color = Color.DarkGray, fontSize = 18.sp) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) { 
                                Icon(Icons.Default.Menu, "Menu", tint = Color.DarkGray) 
                            }
                        },
                        actions = {
                            IconButton(onClick = { navController.navigate(Screen.Profile.route) }) { 
                                Icon(Icons.Default.AccountCircle, "Profile", Modifier.size(28.dp), tint = Color.DarkGray) 
                            }
                        },
                        modifier = Modifier.height(64.dp),
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = SurfaceWhite)
                    )
                },
                bottomBar = { CustomBottomBar(navController, currentDestination) }
            ) { innerPadding ->
                Box(Modifier.fillMaxSize()) {
                    Box(Modifier.padding(top = innerPadding.calculateTopPadding())) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomBottomBar(navController: NavHostController, currentDestination: NavDestination?) {
    val items = listOf(
        NavigationItem("Product", Screen.Home.route, Icons.Filled.Home, Icons.Filled.Home),
        NavigationItem("Search", Screen.Search.route, Icons.Filled.Search, Icons.Outlined.Search),
        NavigationItem("Cart", Screen.Cart.route, Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart),
        NavigationItem("Profile", Screen.Profile.route, Icons.Filled.Person, Icons.Outlined.Person)
    )

    Box(
        modifier = Modifier.fillMaxWidth().height(105.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(90.dp),
            color = Color.White,
            shape = FigmaMountainNotchShape(),
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceAround) {
                    items.take(2).forEach { BottomNavItem(it, navController, currentDestination) }
                }
                Spacer(modifier = Modifier.size(95.dp)) 
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceAround) {
                    items.drop(2).forEach { BottomNavItem(it, navController, currentDestination) }
                }
            }
        }

        FloatingActionButton(
            onClick = { },
            shape = CircleShape,
            containerColor = PrimaryBrown,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(62.dp)
                .offset(y = 6.dp),
            elevation = FloatingActionButtonDefaults.elevation(6.dp)
        ) {
            Icon(Icons.Default.Storefront, "Shop", Modifier.size(32.dp))
        }
    }
}

@Composable
fun BottomNavItem(item: NavigationItem, navController: NavHostController, currentDestination: NavDestination?) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
    val color = if (isSelected) PrimaryBrown else Color(0xFFC4C4C4)
    
    Column(
        modifier = Modifier
            .padding(bottom = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                navController.navigate(item.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Text(text = item.label, color = color, fontSize = 10.sp)
    }
}

data class NavigationItem(
    val label: String,
    val route: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector
)

@Preview(showBackground = true)
@Composable
fun MainAppPreview() { MainApp() }
