package com.ahgitdevelopment.dollarstation.features.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@Composable
fun CircleProgressInd(show: Boolean = false) {
    if (show) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCircleProgressInd() {
    DollarStationTheme {
        CircleProgressInd(true)
    }
}
