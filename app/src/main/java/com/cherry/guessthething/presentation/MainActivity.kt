package com.cherry.guessthething.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: CartoonViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //test
        val list = viewModel.cartoons
        Log.i("bebra", if (list.isEmpty()) "isEmpty" else list[1].toString())

        setContent {
            val navHostController = rememberNavController()
            MainNavHost(navHostController = navHostController, viewModel = viewModel)
        }
    }
}