package com.ahgitdevelopment.dollarstation.features.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HistoryScreen(
    navController: NavController,
    currency: String
) {
    Text(text = currency)
}
