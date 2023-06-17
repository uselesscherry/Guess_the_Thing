package com.moya_utoma.color_data.data.di

import com.moya_utoma.color_data.data.remote.ColorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://colornames.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideColorService(retrofit: Retrofit) =
        retrofit.create(ColorApiService::class.java)

}