package com.example.challenge2tp3.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.challenge2tp3.CustomBottomBar
import com.example.challenge2tp3.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit = {}) {
    var email by remember { mutableStateOf("xxx@gmail.com") }
    var phone by remember { mutableStateOf("+5493123135") }
    var website by remember { mutableStateOf("www.google.com") }
    var password by remember { mutableStateOf("xxxxxxxxxxxxx") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Profile Picture with Edit Icon
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for profile image
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = Color.White
                )
            }
            Surface(
                modifier = Modifier
                    .size(32.dp)
                    .offset(x = (-4).dp, y = (-4).dp),
                shape = CircleShape,
                color = PrimaryBrown,
                shadowElevation = 4.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Profile",
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Martin",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "UI UX DESIGN",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Input Fields
        ProfileTextField(
            label = "E-Mail Address",
            value = email,
            onValueChange = { email = it },
            icon = Icons.Outlined.Email
        )
        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(
            label = "Phone Number",
            value = phone,
            onValueChange = { phone = it },
            icon = Icons.Outlined.Phone
        )
        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(
            label = "Web Site",
            value = website,
            onValueChange = { website = it },
            icon = Icons.Default.Settings // Using settings icon as placeholder for website
        )
        Spacer(modifier = Modifier.height(16.dp))

        ProfileTextField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            icon = Icons.Outlined.Lock
        )

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 12.sp) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    Challenge2TP3Theme(dynamicColor = false) {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = BackgroundBeige,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Profile", color = Color.DarkGray, fontSize = 20.sp) },
                    navigationIcon = {
                        IconButton(onClick = { }) { 
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.DarkGray) 
                        }
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
                    ProfileScreen()
                }
            }
        }
    }
}
