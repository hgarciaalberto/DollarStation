package com.ahgitdevelopment.dollarstation.features.firestore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun FirestoreScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FirestoreViewModel = hiltViewModel()
) {

    val isLoading by viewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::refreshData
    ) {
        FirestoreContent(
//            currencyList = dollarList,
//            error = errorMessage,
            onClick = viewModel::cardClick
        )
    }
}

@Composable
fun FirestoreContent(
//    currencyList: List<Currency>,
//    error: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Firestore")
    }
}

@Preview(showBackground = true)
@Composable
fun FirestorePreview() {
    DollarStationTheme {
        FirestoreContent {}
    }
}
