package com.ahgitdevelopment.dollarstation.features.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.model.local.History
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import com.patrykandpatryk.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatryk.vico.compose.axis.vertical.startAxis
import com.patrykandpatryk.vico.compose.chart.Chart
import com.patrykandpatryk.vico.compose.chart.line.lineChart
import com.patrykandpatryk.vico.core.entry.FloatEntry
import com.patrykandpatryk.vico.core.entry.entryModelOf


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

//    StockChartScreen(currency, dollarList)
    HistoryContent(currency, dollarList)
}


@Composable
fun HistoryContent(
    currencyName: String,
    historyData: List<History> = listOf(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = currencyName)

        val entryModel = entryModelOf(
            historyData.map {
                FloatEntry(
                    it.date.toLocalDate().monthValue.toFloat(),
                    it.value
                )
            }
        )
        Chart(
            modifier = Modifier.padding(8.dp),
            chart = lineChart(),
            model = entryModel,
            startAxis = startAxis(title = "title"),
            bottomAxis = bottomAxis()
//            legend = legend()
        )
    }
}

//@Composable
//private fun legend(): VerticalLegend = verticalLegend(
//    items = entityColors.mapIndexed { index, color ->
//        verticalLegendItem(
//            icon = shapeComponent(shape = Shapes.rectShape, color = Color(color)),
//            label = textComponent(textSize = LEGEND_LABEL_SIZE_SP.sp),
//            labelText = "item $index"
//        )
//    },
//    iconSize = 8.dp,
//    iconPadding = 10.dp,
//    spacing = 4.dp,
//    padding = dimensionsOf(top = 8.dp),
//)
//private val entityColors = longArrayOf(0xFFB983FF, 0xFF94B3FD, 0xFF94DAFF)
//private const val LEGEND_LABEL_SIZE_SP = 12f

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHistoryContent() {
    DollarStationTheme {
        HistoryContent(
            currencyName = "Turista",
            historyData = FAKE_DATA
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStockChartScreen() {
    DollarStationTheme {
        StockChartScreen("Turista", FAKE_DATA)
    }
}