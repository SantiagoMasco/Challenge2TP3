package com.example.challenge2tp3.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.challenge2tp3.ui.theme.PrimaryBrown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(productId: Int?, onBack: () -> Unit = {}) {
    var size by remember { mutableStateOf("") }
    var count by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 1. Selector de talle
        Text(
            text = "Select size",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = size,
            onValueChange = { size = it },
            label = { Text("Label", fontSize = 12.sp) },
            placeholder = { Text("Input") },
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Cantidad de producto
        Text(
            text = "Count of producy",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = count,
            onValueChange = { count = it },
            label = { Text("Label", fontSize = 12.sp) },
            placeholder = { Text("Input") },
            modifier = Modifier.fillMaxWidth(0.85f),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        // 3. Botones de acción inferiores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.size(width = 80.dp, height = 40.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(100.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryBrown)
            ) {
                Text("Back", color = PrimaryBrown, fontSize = 14.sp)
            }

            Button(
                onClick = { /* Comprar */ },
                modifier = Modifier.size(width = 72.dp, height = 40.dp),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBrown)
            ) {
                Text("Buy", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(productId = 1)
}
