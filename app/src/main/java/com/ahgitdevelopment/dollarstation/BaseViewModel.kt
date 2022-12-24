package com.ahgitdevelopment.dollarstation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {
    val isLoading = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")
}
