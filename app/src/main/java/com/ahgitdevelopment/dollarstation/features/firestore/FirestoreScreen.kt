package com.ahgitdevelopment.dollarstation.features.firestore

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.model.local.DbCurrency
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FirestoreScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FirestoreViewModel = hiltViewModel()
) {

    val dollarList by viewModel.dollarList.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsState()

    val pullRefreshState = rememberPullRefreshState(isLoading, viewModel::refreshData)

    Box(modifier = modifier.pullRefresh(pullRefreshState)) {
        FirestoreContent(
            dollarList = dollarList,
            error = errorMessage,
            onClick = viewModel::cardClick
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
fun FirestoreContent(
    dollarList: List<DbCurrency>,
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
                Text(modifier = Modifier.fillMaxWidth(), text = error)
            }

            dollarList.isEmpty() -> {
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
                    items(items = dollarList) {
                        DbCardItem(
                            modifier = modifier,
                            item = it,
                            onClick = onClick
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirestorePreview() {
    DollarStationTheme {
        val dollarList = arrayListOf(
            DbCurrency()
        )

        FirestoreContent(dollarList) {}
    }
}
