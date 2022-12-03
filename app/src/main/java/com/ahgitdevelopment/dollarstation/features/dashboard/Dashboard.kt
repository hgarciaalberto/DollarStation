package com.ahgitdevelopment.dollarstation.features.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import java.util.Calendar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val dollarList by viewModel.dollarList.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    DashboardContent(
        currencyList = dollarList,
        error = errorMessage,
        onClick = viewModel::cardClick
    )
}

@Composable
fun DashboardContent(
    currencyList: List<Currency>,
    error: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            error.isNotBlank() -> {
                Text(text = error)
            }

            currencyList.isEmpty() -> {
                Text(text = "Empty List")
            }

            else -> {
                LazyColumn {
                    items(items = currencyList) {
                        CardItem(it, modifier, onClick)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    item: Currency,
    modifier: Modifier,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = modifier.padding(16.dp),
        enabled = true,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Color.Blue),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(text = "Currency: ${item.name}")
            Text(text = "Compra: ${item.buy}")
            Text(text = "Venta: ${item.sell}")
            Text(text = "Date: ${item.date}")
            Text(text = "Variacion: ${item.variation}%")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {

    val FAKE_CURRENCIES = listOf(
        Currency(
            name = "blue",
            buy = 1.1f,
            sell = 1.1f,
            date = Calendar.getInstance().time,
            variation = 1.2f,
        ),
        Currency(
            name = "nacion",
            buy = 1.1f,
            sell = 1.1f,
            date = Calendar.getInstance().time,
            variation = 1.2f,
        ),
        Currency(
            name = "soja",
            buy = 1.1f,
            sell = 1.1f,
            date = Calendar.getInstance().time,
            variation = 1.2f,
        )
    )

    DollarStationTheme {
        DashboardContent(FAKE_CURRENCIES, "") {}
    }
}
