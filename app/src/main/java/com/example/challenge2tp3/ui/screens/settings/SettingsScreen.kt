package com.example.challenge2tp3.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.challenge2tp3.CustomBottomBar
import com.example.challenge2tp3.ui.theme.*

@Composable
fun SettingsScreen() {
    var pushNotifications by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Account Settings Section
        Text(
            text = "Account Settings",
            fontSize = 18.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SettingsItem("Edit profile", hasNavigation = true)
        SettingsItem("Change password", hasNavigation = true)
        
        SettingsSwitchItem(
            title = "Push notifications",
            checked = pushNotifications,
            onCheckedChange = { pushNotifications = it }
        )
        
        SettingsSwitchItem(
            title = "Dark mode",
            checked = darkMode,
            onCheckedChange = { darkMode = it }
        )

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
        Spacer(modifier = Modifier.height(16.dp))

        // More Section
        Text(
            text = "More",
            fontSize = 18.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SettingsItem("About us", hasNavigation = true)
        SettingsItem("Privacy policy", hasNavigation = true)
        SettingsItem("Terms and conditions", hasNavigation = false)

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
fun SettingsItem(title: String, hasNavigation: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { /* Action */ },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        if (hasNavigation) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun SettingsSwitchItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PrimaryBrown,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp", showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    Challenge2TP3Theme(dynamicColor = false) {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = BackgroundBeige,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Settings", color = Color.DarkGray, fontSize = 20.sp) },
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
                    SettingsScreen()
                }
            }
        }
    }
}
