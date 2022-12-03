package com.ahgitdevelopment.dollarstation.di

import com.ahgitdevelopment.dollarstation.network.DollarApi
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

    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    @Provides
    fun providesDollarApi(retrofit: Retrofit): DollarApi =
        retrofit.create(DollarApi::class.java)
}
