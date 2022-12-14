package com.ahgitdevelopment.dollarstation.features.dashboard

import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.BaseViewModel
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    val dollarList = MutableStateFlow(listOf<Currency>())

    init {
        refreshData()
    }

    fun refreshData() = viewModelScope.launch {
        isLoading.value = true
        repository.getDollars().fold(
            onSuccess = {
                dollarList.value = it
                isLoading.value = false
            },
            onFailure = {
                errorMessage.value = "Error: ${it.message}"
                isLoading.value = false
            }
        )
    }
}
