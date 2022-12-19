package com.ahgitdevelopment.dollarstation.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val dollarList = MutableStateFlow(listOf<Currency>())
    val errorMessage = MutableStateFlow("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        refreshData()
    }

    fun refreshData() = viewModelScope.launch {
        _isLoading.value = true
        repository.getDollars().fold(
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
}
