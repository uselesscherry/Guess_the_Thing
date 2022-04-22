package com.cherry.guessthething.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.cherry.guessthething.CartoonViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: CartoonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = CartoonViewModel(application)

        //test
        val list = viewModel.cartoons
            Log.i("bebra", if (list.isEmpty())"isEmpty" else list[1].toString())

        setContent {
            val navHostController = rememberNavController()
            MainNavHost(navHostController = navHostController, viewModel = viewModel)
        }
    }
}