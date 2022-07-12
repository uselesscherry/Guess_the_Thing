package com.cherry.guessthething.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CartoonApp:Application() {

    override fun onCreate() {
        super.onCreate()
startKoin {
    androidContext(this@CartoonApp)
    modules(appModule)
}
    }
}