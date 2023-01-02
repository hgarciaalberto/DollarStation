package com.ahgitdevelopment.dollarstation.features.api2

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.features.firestore.FirestoreContent

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun Api2Screen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: Api2ViewModel = hiltViewModel()
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
