package com.ahgitdevelopment.dollarstation.features.api2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.model.local.DbCurrency
import com.ahgitdevelopment.dollarstation.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Api2ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val dollarList = MutableStateFlow(listOf<DbCurrency>())
    val errorMessage = MutableStateFlow("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        refreshData()
    }

    fun refreshData() = viewModelScope.launch {
        _isLoading.value = true
        repository.getDollarsApi2().fold(
            onSuccess = {
                dollarList.value = it
                _isLoading.value = false
            },
            onFailure = {
                errorMessage.value = "Error: ${it.message}"
                _isLoading.value = false
            }
        )
    }

    fun cardClick() {
    }
}
