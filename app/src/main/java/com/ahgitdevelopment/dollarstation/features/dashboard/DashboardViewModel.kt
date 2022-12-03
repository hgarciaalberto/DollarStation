package com.ahgitdevelopment.dollarstation.features.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.model.local.Currency
import com.ahgitdevelopment.dollarstation.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {

    val dollarList = MutableStateFlow(listOf<Currency>())
    val errorMessage = MutableStateFlow("")

    init {
        viewModelScope.launch {
            repository.getDollars().fold(
                onSuccess = {
                    dollarList.value = it
                },
                onFailure = {
                    errorMessage.value = "Error: ${it.message}"
                }
            )
        }
    }

    fun cardClick() {
    }
}
