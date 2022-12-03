package com.ahgitdevelopment.dollarstation.features

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahgitdevelopment.dollarstation.features.dashboard.DashboardViewModel
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    DashboardContent("World")
}

@Composable
fun DashboardContent(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DollarStationTheme {
        DashboardContent("Android")
    }
}