package com.ahgitdevelopment.dollarstation.features.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahgitdevelopment.dollarstation.model.local.CurrencyType
import com.ahgitdevelopment.dollarstation.model.local.History
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@Composable
fun MPAndroidChart(currency: String, historyData: List<History>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(text = CurrencyType.getType(currency).fullName)
        Spacer(modifier = Modifier.height(32.dp))
        MPAndroidContent()
    }
}

@Composable
fun MPAndroidContent() {
    Text("Nothing")
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMPAndroid() {
    DollarStationTheme {
        Column {
            MPAndroidContent()
        }
    }
}