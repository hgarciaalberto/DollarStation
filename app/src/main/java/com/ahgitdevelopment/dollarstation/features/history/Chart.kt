package com.ahgitdevelopment.dollarstation.features.history

import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahgitdevelopment.dollarstation.model.local.CurrencyType
import com.ahgitdevelopment.dollarstation.model.local.History
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import java.time.LocalDateTime
import kotlin.math.round


@Composable
fun StockChartScreen(currency: String, historyData: List<History>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(text = CurrencyType.getType(currency).fullName)
        Spacer(modifier = Modifier.height(32.dp))
        StockChart(
            historyData = historyData,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(CenterHorizontally)
        )
    }
}

@Composable
fun StockChart(
    historyData: List<History> = emptyList(),
    modifier: Modifier = Modifier,
    graphColor: Color = Color.Blue
) {
    val spacing = 100f
    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }
    val upperValue = remember(historyData) {
        historyData.maxOfOrNull { it.value } ?: 0f
    }
    val lowerValue = remember(historyData) {
        historyData.minOfOrNull { it.value } ?: 0f
    }
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / historyData.size
        (0 until historyData.size - 1 step 2).forEach { i ->
            val info = historyData[i]
            val hour = "${info.date.toLocalDate().dayOfMonth}"
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(), spacing + i * spacePerHour, size.height - 5, textPaint
                )
            }
        }
        val priceStep = (upperValue - lowerValue) / 5f
        (0..4).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + priceStep * i).toString(),
                    30f,
                    size.height - spacing - i * size.height / 5f,
                    textPaint
                )
            }
        }
        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            for (i in historyData.indices) {
                val info = historyData[i]
                val nextInfo = historyData.getOrNull(i + 1) ?: historyData.last()
                val leftRatio = (info.value - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo.value - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * spacePerHour
                val y1 = height - spacing - (leftRatio * height).toFloat()
                val x2 = spacing + (i + 1) * spacePerHour
                val y2 = height - spacing - (rightRatio * height).toFloat()
                if (i == 0) {
                    moveTo(x1, y1)
                }
                lastX = (x1 + x2) / 2f
                quadTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )
            }
        }
//        val fillPath = Path().asComposePath().apply {
//            lineTo(lastX, size.height - spacing)
//            lineTo(spacing, size.height - spacing)
//            close()
//        }
//        drawPath(
//            path = fillPath, brush = Brush.verticalGradient(
//                colors = listOf(
//                    transparentGraphColor, Color.Transparent
//                ), endY = size.height - spacing
//            )
//        )
        drawPath(
            path = strokePath.asComposePath(), color = graphColor, style = Stroke(
                width = 3.dp.toPx(), cap = StrokeCap.Round
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewChart() {
    DollarStationTheme {
        Column {
            StockChart(
                historyData = FAKE_DATA,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(CenterHorizontally)
            )
        }
    }
}

val FAKE_DATA = listOf(
    History(LocalDateTime.now().plusDays(0), 12.2f),
    History(LocalDateTime.now().plusDays(1), 15.2f),
    History(LocalDateTime.now().plusDays(2), 25.2f),
    History(LocalDateTime.now().plusDays(3), 13.2f),
    History(LocalDateTime.now().plusDays(4), 17.2f),
    History(LocalDateTime.now().plusDays(5), 8.2f),
    History(LocalDateTime.now().plusDays(6), 12.2f),
    History(LocalDateTime.now().plusDays(7), 7.2f),
    History(LocalDateTime.now().plusDays(8), 3.2f),
    History(LocalDateTime.now().plusDays(9), 8.2f),
    History(LocalDateTime.now().plusDays(10), 12.2f),
    History(LocalDateTime.now().plusDays(11), 6.2f),
    History(LocalDateTime.now().plusDays(12), 8.2f),
    History(LocalDateTime.now().plusDays(13), 11.2f)
)