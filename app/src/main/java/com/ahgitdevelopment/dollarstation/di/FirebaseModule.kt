package com.ahgitdevelopment.dollarstation.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

const val ROOT_DATABASE = "dolar_exchange"

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun providesFirestore(): FirebaseFirestore = Firebase.firestore

}
