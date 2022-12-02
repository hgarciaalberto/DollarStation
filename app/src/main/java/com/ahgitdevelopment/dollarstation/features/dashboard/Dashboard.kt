package com.ahgitdevelopment.dollarstation.features

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahgitdevelopment.dollarstation.features.dashboard.DashboardViewModel
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = viewModel() // TODO: Change when adding Hilt
) {
    DashboardContent("World")
}

@Composable
fun DashboardContent(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    DollarStationTheme {
        DashboardContent("Android")
    }
}