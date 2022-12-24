package com.ahgitdevelopment.dollarstation.features.history

import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.BaseViewModel
import com.ahgitdevelopment.dollarstation.model.local.History
import com.ahgitdevelopment.dollarstation.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    val historyData = MutableStateFlow(listOf<History>())

    fun init(currency: String) = viewModelScope.launch {
        isLoading.value = true
        repository.getHistory(currency).fold(
            onSuccess = { value ->
                historyData.value = value
                isLoading.value = false
            },
            onFailure = {
                errorMessage.value = "Error: ${it.message}"
                isLoading.value = false
            }
        )
    }
}
