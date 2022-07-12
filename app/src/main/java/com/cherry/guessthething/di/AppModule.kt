package com.cherry.guessthething.di

import androidx.room.Room
import com.cherry.guessthething.data.RepositoryImpl
import com.cherry.guessthething.data.local.CartoonDatabase
import com.cherry.guessthething.data.remote.ResponseService
import com.cherry.guessthething.data.remote.ResponseServiceImpl
import com.cherry.guessthething.domain.Repository
import com.cherry.guessthething.presentation.CartoonViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            CartoonDatabase::class.java,
            CartoonDatabase.DB_NAME
        ).build()
    }

    single {
        val db = get<CartoonDatabase>()
        db.cartoonDao()
    }

    single<Repository> { RepositoryImpl(androidContext(),get(), get()) }

    viewModel { CartoonViewModel(get()) }

    single<ResponseService> {
        ResponseServiceImpl(HttpClient(Android))
    }

}