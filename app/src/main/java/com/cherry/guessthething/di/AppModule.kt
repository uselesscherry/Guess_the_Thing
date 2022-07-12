package com.cherry.guessthething.di

import androidx.room.Room
import com.cherry.guessthething.data.RepositoryImpl
import com.cherry.guessthething.data.local.CartoonDatabase
import com.cherry.guessthething.domain.Repository
import com.cherry.guessthething.presentation.CartoonViewModel
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

    single<Repository> { RepositoryImpl(androidContext(), cartoonDao = get()) }

    viewModel { CartoonViewModel(get()) }

}