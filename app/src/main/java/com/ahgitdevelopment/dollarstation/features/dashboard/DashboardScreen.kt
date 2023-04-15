package com.ahgitdevelopment.dollarstation.features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.model.local.CurrencyType
import com.ahgitdevelopment.dollarstation.navigation.AppScreens
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import java.time.LocalDateTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(
    navController: NavController, modifier: Modifier = Modifier, viewModel: DashboardViewModel = hiltViewModel()
) {

    val dollarList by viewModel.dollarList.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    val pullRefreshState = rememberPullRefreshState(isLoading, viewModel::refreshData)

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        DashboardContent(
            currencyList = dollarList,
            error = errorMessage,
            onClick = { currency ->
                navController.navigate(AppScreens.HistoryScreen.createRoute(currency.key))
            }
        )
        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            scale = true,
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun DashboardContent(
    currencyList: List<Currency>, error: String = "", modifier: Modifier = Modifier, onClick: (CurrencyType) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            error.isNotBlank() -> {
                Text(modifier = Modifier.fillMaxWidth(), text = error)
            }

            currencyList.isEmpty() -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        textAlign = TextAlign.Center, text = "Empty List"
                    )
                }
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

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {

    val fakeCurrencies = listOf(
        Currency(
            currencyType = CurrencyType.TURISTA,
            buy = 1.2f,
            sell = 1.2f,
            date = LocalDateTime.now(),
            variation = 1.2222f,
        ), Currency(
            currencyType = CurrencyType.NACION,
            buy = 1.1f,
            sell = 1.1f,
            date = LocalDateTime.now().plusHours(1),
            variation = -1.2f,
        ), Currency(
            currencyType = CurrencyType.COLDPLAY,
            buy = 1.1f,
            sell = 1.1f,
            date = LocalDateTime.now().plusHours(2),
            variation = 1.2f,
        )
    )

    DollarStationTheme {
        DashboardContent(fakeCurrencies, "") {}
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardEmptyListPreview() {

    val emptyList = listOf<Currency>()

    DollarStationTheme {
        DashboardContent(emptyList, "") {}
    }
}
