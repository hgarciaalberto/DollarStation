package com.ahgitdevelopment.dollarstation.features.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val currencies = MutableStateFlow(listOf<String>())
    val exchange = MutableStateFlow("")
    val errorMessage = MutableStateFlow("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getDollars().fold(
                onSuccess = {
                    currencies.value = it.map { currency -> currency.currencyType.fullName }
                    _isLoading.value = false
                },
                onFailure = {
                    errorMessage.value = "Error: ${it.message}"
                    _isLoading.value = false
                }
            )
        }
    }

    fun exchange(
        exchangeType: ExchangeType = ExchangeType.BUY,
        currency: String,
        value: String = "0.0"
    ) {
        if (currency.isNullOrEmpty()) {
            exchange.value = ""
        } else {
            _isLoading.value = true
            viewModelScope.launch {
                repository.calculator(exchangeType, currency, value).fold(
                    onSuccess = {
                        exchange.value = String.format("$%.2f ars", it.toFloat())
                        _isLoading.value = false
                    },
                    onFailure = {
                        errorMessage.value = "Error: ${it.message}"
                        _isLoading.value = false
                    }
                )
            }
        }
    }

    enum class ExchangeType {
        BUY,
        SELL
    }
}
