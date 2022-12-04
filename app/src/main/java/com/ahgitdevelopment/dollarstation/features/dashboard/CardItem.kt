package com.ahgitdevelopment.dollarstation.features.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ahgitdevelopment.dollarstation.R
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    item: Currency, modifier: Modifier = Modifier, onClick: () -> Unit
) {

    Card(onClick = onClick,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        enabled = true,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Color.Blue),
        interactionSource = remember { MutableInteractionSource() }) {

        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Create references for the composables to constrain
            val (name, arrow, variation, buy, sell, date) = createRefs()

            Text(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(parent.top)
                    bottom.linkTo(buy.top)
                    start.linkTo(parent.start)
                    end.linkTo(arrow.start)
                }, text = item.name, color = Color.Blue, fontSize = 20.sp
            )

            Icon(modifier = Modifier
                .constrainAs(arrow) {
                    top.linkTo(name.top)
                    bottom.linkTo(name.bottom)
                    start.linkTo(name.end)
                    end.linkTo(variation.start)
                }
                .size(50.dp),
                imageVector = if (item.variation > 0) Icons.Default.ArrowUpward
                else Icons.Default.ArrowDownward,
                tint = item.variation.getRedGreenColor(),
                contentDescription = "Arrow")

            Text(
                modifier = Modifier.constrainAs(variation) {
                    top.linkTo(arrow.top)
                    bottom.linkTo(arrow.bottom)
                    start.linkTo(arrow.end)
                    end.linkTo(parent.end)
                },
                text = "${item.variation.twoDecimals()}%",
                color = item.variation.getRedGreenColor(),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )

            Column(modifier = Modifier.constrainAs(buy) {
                top.linkTo(name.bottom, margin = 16.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(sell.start)
            }) {
                Text(text = "Compra", color = Color.Gray)
                Text(
                    text = item.buy.twoDecimals(),
                    color = Color.Black, fontSize = 25.sp
                )
            }

            Column(modifier = Modifier.constrainAs(sell) {
                top.linkTo(buy.top)
                bottom.linkTo(buy.bottom)
                start.linkTo(buy.end)
                end.linkTo(date.start)
            }) {

                Text(text = "Venta", color = Color.Gray)
                Text(
                    text = item.sell.twoDecimals(),
                    color = Color.Black, fontSize = 25.sp
                )
            }

            Column(modifier = Modifier.constrainAs(date) {
                top.linkTo(sell.top)
                bottom.linkTo(sell.bottom)
                start.linkTo(sell.end)
                end.linkTo(parent.end)
            }) {

                Text(
                    text = "Fecha",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "11/12/2022",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

private fun Float.twoDecimals(): String = String.format("%.2f", this)

@Preview
@Composable
fun PreviewCardItem() {

    val FAKE_CURRENCY = Currency(
        name = "Dólar Blue",
        buy = 1.1f,
        sell = 1.1f,
        date = Calendar.getInstance().time,
        variation = 1.2f,
    )

    DollarStationTheme {
        CardItem(FAKE_CURRENCY) {}
    }
}

@Composable
private fun Float.getRedGreenColor(): Color = if (this > 0) colorResource(R.color.green)
else colorResource(R.color.red)
