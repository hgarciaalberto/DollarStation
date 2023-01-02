package com.ahgitdevelopment.dollarstation.features.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.model.local.CurrencyType
import com.ahgitdevelopment.dollarstation.model.local.History
import java.time.LocalDateTime

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    currency: String,
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val dollarList by viewModel.historyData.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.init(currency)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Text(text = CurrencyType.getType(currency).fullName)
        Spacer(modifier = Modifier.height(32.dp))

        MPAndroidContent(currency, dollarList)
    }
}

var i: Long = 0
val FAKE_DATA = listOf(
    History(LocalDateTime.now().plusDays(i++), 15.2f),
    History(LocalDateTime.now().plusDays(i++), 25.2f),
    History(LocalDateTime.now().plusDays(i++), 13.2f),
    History(LocalDateTime.now().plusDays(i++), 17.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 12.2f),
    History(LocalDateTime.now().plusDays(i++), 7.2f),
    History(LocalDateTime.now().plusDays(i++), 3.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 9.2f),
    History(LocalDateTime.now().plusDays(i++), 6.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 12.2f),
    History(LocalDateTime.now().plusDays(i++), 15.2f),
    History(LocalDateTime.now().plusDays(i++), 22.2f),
    History(LocalDateTime.now().plusDays(i++), 13.2f),
    History(LocalDateTime.now().plusDays(i++), 17.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 12.2f),
    History(LocalDateTime.now().plusDays(i++), 7.2f),
    History(LocalDateTime.now().plusDays(i++), 3.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 12.2f),
    History(LocalDateTime.now().plusDays(i++), 11.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 12.2f),
    History(LocalDateTime.now().plusDays(i++), 15.2f),
    History(LocalDateTime.now().plusDays(i++), 22.2f),
    History(LocalDateTime.now().plusDays(i++), 19.2f),
    History(LocalDateTime.now().plusDays(i++), 17.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 12.2f),
    History(LocalDateTime.now().plusDays(i++), 7.2f),
    History(LocalDateTime.now().plusDays(i++), 3.2f),
    History(LocalDateTime.now().plusDays(i++), 1.2f),
    History(LocalDateTime.now().plusDays(i++), 2.2f),
    History(LocalDateTime.now().plusDays(i++), 5.2f),
    History(LocalDateTime.now().plusDays(i++), 8.2f),
    History(LocalDateTime.now().plusDays(i++), 11.2f)
)