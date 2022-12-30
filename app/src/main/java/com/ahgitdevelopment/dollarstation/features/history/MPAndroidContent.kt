package com.ahgitdevelopment.dollarstation.features.history

import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ahgitdevelopment.dollarstation.model.local.History
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter

@Composable
fun MPAndroidContent(
    currencyName: String,
    historyData: List<History> = listOf(),
    modifier: Modifier = Modifier
) {
    // on below line we are creating a cross fade and
    // specifying target state as pie chart data the
    // method we have created in Pie chart data class.
    Crossfade(targetState = historyData) { pieChartData ->
        // on below line we are creating an
        // android view for pie chart.
        AndroidView(factory = { context ->
            // on below line we are creating a pie chart
            // and specifying layout params.
            LineChart(context).apply {

                layoutParams = LinearLayout.LayoutParams(
                    // on below line we are specifying layout
                    // params as MATCH PARENT for height and width.
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )

                description.isEnabled = false
                setTouchEnabled(true)
                dragDecelerationFrictionCoef = 0.9f

                // enable scaling and dragging
                isDragEnabled = true
                setScaleEnabled(true)
                setDrawGridBackground(false)
                isHighlightPerDragEnabled = true

                // set an alternative background color
                setBackgroundColor(Color.WHITE)

                setPinchZoom(true)

                xAxis.apply {
                    isEnabled = true
                    setLabelCount(10, false)
                    textColor = Color.BLACK
                    position = XAxis.XAxisPosition.BOTTOM
                }

                axisLeft.apply {
                    isEnabled = true
                    setLabelCount(10, false)
                    textColor = Color.BLACK
                    setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
                    setDrawGridLines(false)
                    axisLineColor = Color.BLACK
                }

                axisRight.isEnabled = false

                animateXY(1000, 2000)

                legend.apply {
                    isEnabled = false
                    textSize = 14f
                    horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                }
            }
        },
            // on below line we are specifying modifier
            // for it and specifying padding to it.
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            update = {
                // on below line we are calling update pie chart
                // method and passing pie chart and list of data.
                updatePieChartWithData(it, pieChartData)
            })
    }
}

// on below line we are creating a update pie
// chart function to update data in pie chart.
fun updatePieChartWithData(
    chart: LineChart,
    data: List<History>
) {

    val entries = ArrayList<Entry>()
    data.mapIndexed { index, history ->
        entries.add(Entry(index.toFloat(), history.value))
    }

    val ds = LineDataSet(entries, "label").apply {
        mode = LineDataSet.Mode.CUBIC_BEZIER
        cubicIntensity = 0.2f
        setDrawFilled(true)
        setDrawCircles(false)
        lineWidth = 1.8f
        circleRadius = 4f
        highLightColor = Color.YELLOW
        color = Color.BLUE
        setDrawHorizontalHighlightIndicator(true)
        fillFormatter = IFillFormatter { _, _ ->
            chart.axisLeft.axisMinimum
        }
    }

    val d = LineData(ds).apply {
        setValueTypeface(Typeface.DEFAULT)
        setValueTextSize(9f)
        setDrawValues(false)
    }

    chart.data = d

    // on below line we are calling invalidate in chart.
    chart.invalidate()
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMPAndroid() {
    DollarStationTheme {
        Column {
            MPAndroidContent(currencyName = "Turista", historyData = FAKE_DATA)
        }
    }
}