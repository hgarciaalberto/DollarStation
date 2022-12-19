package com.ahgitdevelopment.dollarstation.features.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.navigation.AppScreens
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import java.util.Calendar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DashboardScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val dollarList by viewModel.dollarList.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberPullRefreshState(isLoading, onRefresh =)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::refreshData
    ) {
        DashboardContent(
            currencyList = dollarList,
            error = errorMessage,
            onClick = { currency ->
                navController.navigate(AppScreens.HistoryScreen.createRoute(currency))
            }
        )
    }
}

@Composable
fun DashboardContent(
    currencyList: List<Currency>,
    error: String = "",
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
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
                        textAlign = TextAlign.Center,
                        text = "Empty List"
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
            name = "blue",
            buy = 1.1f,
            sell = 1.1f,
            date = Calendar.getInstance().time,
            variation = 1.2222f,
        ),
        Currency(
            name = "nacion",
            buy = 1.1f,
            sell = 1.1f,
            date = Calendar.getInstance().time,
            variation = -1.2f,
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
