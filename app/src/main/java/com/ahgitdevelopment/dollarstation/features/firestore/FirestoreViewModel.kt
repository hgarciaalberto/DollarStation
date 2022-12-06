package com.ahgitdevelopment.dollarstation.features.firestore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.model.local.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(
) : ViewModel() {

    val dollarList = MutableStateFlow(listOf<Currency>())
    val errorMessage = MutableStateFlow("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        refreshData()
    }

    fun refreshData() = viewModelScope.launch {

    }

    fun cardClick() {
    }
}
