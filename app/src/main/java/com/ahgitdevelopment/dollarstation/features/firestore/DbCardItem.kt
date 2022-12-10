package com.ahgitdevelopment.dollarstation.features.firestore

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahgitdevelopment.dollarstation.extensions.getRedGreenColor
import com.ahgitdevelopment.dollarstation.extensions.parseDateFormat
import com.ahgitdevelopment.dollarstation.extensions.twoDecimals
import com.ahgitdevelopment.dollarstation.model.local.DbCurrency
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DbCardItem(
    modifier: Modifier = Modifier,
    item: DbCurrency,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        enabled = true,
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(2.dp, Color.Blue),
        interactionSource = remember { MutableInteractionSource() }) {

        Column(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    modifier = modifier.weight(3f),
                    text = item.currency,
                    color = Color.Blue,
                    fontSize = 20.sp
                )

                Icon(
                    modifier = Modifier
                        .weight(0.6f)
                        .height(40.dp),
                    imageVector = if (item.variacion.replace("%", "").replace(',', '.')
                            .toFloat() > 0
                    ) Icons.Default.ArrowUpward
                    else Icons.Default.ArrowDownward,
                    tint = item.variacion.replace("%", "").replace(',', '.').toFloat()
                        .getRedGreenColor(),
                    contentDescription = "Arrow"
                )

                Text(
                    modifier = modifier
                        .weight(1.4f)
                        .wrapContentSize(),
                    text = "${
                        item.variacion.replace("%", "").replace(",", ".").toFloat().twoDecimals()
                    }%",
                    color = item.variacion.replace("%", "").replace(",", ".").toFloat()
                        .getRedGreenColor(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                TitleContent(
                    "Compra",
                    "$" + item.compra.replace(",", ".").toFloat().twoDecimals()
                )

                TitleContent(
                    "Venta",
                    "$" + item.venta.replace(",", ".").toFloat().twoDecimals()
                )

                TitleContent(
                    "Fecha",
                    item.fecha.parseDateFormat()
                )
            }
        }
    }
}

@Composable
fun TitleContent(title: String, content: String) {
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title, color = Color.Gray, fontSize = 12.sp
        )
        Text(
            text = content, color = Color.Black, fontSize = 18.sp, textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewCardItem() {

    val fakeDbCurrency = DbCurrency(
        "currency", "2,50", "6,54", "12/12/2022", "0,25%"
    )

    DollarStationTheme {
        DbCardItem(
            item = fakeDbCurrency
        ) {}
    }
}
