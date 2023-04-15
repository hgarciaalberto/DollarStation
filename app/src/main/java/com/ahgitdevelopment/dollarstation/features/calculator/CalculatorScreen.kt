package com.ahgitdevelopment.dollarstation.features.calculator

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.dollarstation.extensions.getKeyFromCurrencyName
import com.ahgitdevelopment.dollarstation.features.calculator.CalculatorViewModel.ExchangeType
import com.ahgitdevelopment.dollarstation.features.calculator.CalculatorViewModel.ExchangeType.BUY
import com.ahgitdevelopment.dollarstation.features.calculator.CalculatorViewModel.ExchangeType.SELL
import com.ahgitdevelopment.dollarstation.features.commons.CircleProgressInd
import com.ahgitdevelopment.dollarstation.features.commons.CustomSwitch
import com.ahgitdevelopment.dollarstation.model.local.CurrencyType
import com.ahgitdevelopment.dollarstation.ui.theme.DollarStationTheme

@Composable
fun CalculatorScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = hiltViewModel()
) {

    val currencies by viewModel.currencies.collectAsStateWithLifecycle()
    val exchange by viewModel.exchange.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsState()

    if (currencies.isNotEmpty())
        CalculatorContent(
            modifier,
            currencies,
            exchange,
            error = errorMessage,
            isLoading = isLoading
        ) { currency, value, exchangeType ->
            viewModel.exchange(exchangeType = exchangeType, currency = currency, value = value)
        }
    else {
        CircleProgressInd(true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorContent(
    modifier: Modifier = Modifier,
    currencies: List<String>,
    exchange: String,
    error: String = "",
    isLoading: Boolean = false,
    valueChange: (String, String, ExchangeType) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(currencies[0]) }

    val scrollState = rememberScrollState()
    val isScrolling = scrollState.isScrollInProgress

    var currencyValueText by remember { mutableStateOf("") }
    var exchangeTypeValue by remember { mutableStateOf(false) }

    if (isLoading)
        CircleProgressInd(isLoading)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        ExposedDropdownMenuBox(
            modifier = modifier,
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded && !isScrolling
            }
        ) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                readOnly = true,
                label = { Text("Currencies", fontSize = 16.sp) },
                value = selectedOptionText,
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                onValueChange = { currency ->
                    selectedOptionText = currency
                    valueChange(
                        selectedOptionText.getKeyFromCurrencyName(),
                        currencyValueText,
                        if (exchangeTypeValue) SELL else BUY
                    )
                    if (exchangeTypeValue) SELL else BUY
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    containerColor = Color.White
                ),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                currencies.forEach { selectionOption ->
                    DropdownMenuItem(
                        modifier = modifier,
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            valueChange(
                                selectedOptionText.getKeyFromCurrencyName(),
                                currencyValueText,
                                if (exchangeTypeValue) SELL else BUY
                            )
                        },
                        text = {
                            Text(text = selectionOption, fontSize = 16.sp)
                        }
                    )
                }
            }
        }

        TextField(
            value = currencyValueText,
            textStyle = TextStyle(
                fontSize = 20.sp
            ),
            onValueChange = {
                currencyValueText = it
                valueChange(
                    selectedOptionText.getKeyFromCurrencyName(),
                    currencyValueText,
                    if (exchangeTypeValue) SELL else BUY
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .onFocusChanged {
                    if (it.hasFocus)
                        currencyValueText = ""
                },
            label = { Text("Cantidad", fontSize = 16.sp) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                containerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = exchange,
            textStyle = TextStyle(
                fontSize = 20.sp
            ),
            onValueChange = {},
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .focusable(false),
            enabled = true,
            readOnly = true,
            label = { Text("Cambio", fontSize = 16.sp) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                containerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(fontSize = 20.sp, text = "Compra")

            CustomSwitch(exchangeTypeValue.equals(SELL)) { isChecked ->
                valueChange(
                    selectedOptionText.getKeyFromCurrencyName(),
                    currencyValueText,
                    if (isChecked) SELL else BUY
                )
            }

            Text(fontSize = 20.sp, text = "Venta")
        }

        if (error.isNotEmpty() && currencyValueText.isEmpty())
            Text(
                text = error,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .focusable(false)
            )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculator() {

    val currencies = listOf(
        "dolar_turista",
        "dolar_qatar",
        "dolar_informal",
        "dolar_lujo"
    ).map { CurrencyType.getType(it).fullName }

    DollarStationTheme {
        CalculatorContent(
            currencies = currencies,
            exchange = ""
        ) { _, _, _ -> }
    }
}
