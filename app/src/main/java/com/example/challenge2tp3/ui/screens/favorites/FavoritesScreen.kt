package com.example.challenge2tp3.ui.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.challenge2tp3.CustomBottomBar
import com.example.challenge2tp3.R
import com.example.challenge2tp3.ui.screens.home.Product
import com.example.challenge2tp3.ui.theme.*

@Composable
fun FavoritesScreen() {
    val favoriteProducts = listOf(
        Product(1, "Leather boots", "27,5 $", "", R.drawable.leather_boots),
        Product(2, "Leather boots", "27,5 $", "", R.drawable.leather_boots),
        Product(3, "Leather boots", "27,5 $", "", R.drawable.leather_boots)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 120.dp)
            ) {
                itemsIndexed(favoriteProducts) { index, product ->
                    FavoriteProductCard(index + 1, product)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Button(
                            onClick = { /* Buy action */ },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBrown),
                            shape = RoundedCornerShape(100.dp),
                            modifier = Modifier.height(48.dp).width(120.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp)
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Buy", fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteProductCard(index: Int, product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Index Circle
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(PrimaryBrown),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = index.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            // Info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = product.price,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Image
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Composable
fun FavoritesScreenPreview() {
    Challenge2TP3Theme(dynamicColor = false) {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = BackgroundBeige,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Favourites", color = Color.DarkGray, fontSize = 20.sp) },
                    navigationIcon = {
                        IconButton(onClick = { }) { Icon(Icons.Default.Menu, "Menu", tint = Color.DarkGray) }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.AccountCircle, "Profile", Modifier.size(32.dp), tint = Color.DarkGray)
                        }
                    },
                    modifier = Modifier.height(64.dp),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = SurfaceWhite)
                )
            },
            bottomBar = { CustomBottomBar(navController, null) }
        ) { innerPadding ->
            Box(Modifier.fillMaxSize()) {
                Box(Modifier.padding(top = innerPadding.calculateTopPadding())) {
                    FavoritesScreen()
                }
            }
        }
    }
}
