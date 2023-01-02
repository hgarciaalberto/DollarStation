package com.ahgitdevelopment.dollarstation.features.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(8.dp),
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            textAlign = TextAlign.Center,
            text = CurrencyType.getType(currency).fullName,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
        )

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