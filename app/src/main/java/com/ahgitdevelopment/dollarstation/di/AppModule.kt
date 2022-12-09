package com.ahgitdevelopment.dollarstation.di

import com.ahgitdevelopment.dollarstation.network.DollarApi
import com.ahgitdevelopment.dollarstation.network.DollarApi2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://8w1kj1.deta.dev/"
    private const val BASE_URL2 = "http://dolar-api.ddns.net:8081/"

    @Provides
    fun providesDollarApi(): DollarApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(DollarApi::class.java)

    @Provides
    fun providesDollarApi2(): DollarApi2 =
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(DollarApi2::class.java)

}
