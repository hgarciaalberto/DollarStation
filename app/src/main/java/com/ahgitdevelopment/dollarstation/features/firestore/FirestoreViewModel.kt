package com.ahgitdevelopment.dollarstation.features.firestore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.dollarstation.di.ROOT_DATABASE
import com.ahgitdevelopment.dollarstation.model.local.DbCurrency
import com.ahgitdevelopment.dollarstation.model.mapper.getCurrencyName
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    val dollarList = MutableStateFlow(listOf<DbCurrency>())

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        refreshData()
    }

    fun refreshData() = viewModelScope.launch {
        _isLoading.value = true

        val docRef = firestore.collection(ROOT_DATABASE)
        docRef.get()
            .addOnSuccessListener { documents ->
                val dbCurrencies = arrayListOf<DbCurrency>()

                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    if (document.exists()) {
                        document.toObject(DbCurrency::class.java).let {
                            dbCurrencies.add(it)
                        }
                    }
                }
                dollarList.value = dbCurrencies.map {
                    it.apply {
                        currency = currency.getCurrencyName()
                    }
                }
                _isLoading.value = false
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
                _errorMessage.value = exception.message ?: "Error getting documents"
                _isLoading.value = false
            }
    }

    fun cardClick() {
    }

    companion object {
        private const val TAG = "FirestoreViewModel"
    }
}
