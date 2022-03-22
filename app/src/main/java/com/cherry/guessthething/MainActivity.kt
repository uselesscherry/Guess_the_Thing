package com.cherry.guessthething

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.cherry.guessthething.domain.CartoonViewModel
import com.cherry.guessthething.view.MainNavHost

class MainActivity : ComponentActivity() {

    private lateinit var viewModel:CartoonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = CartoonViewModel()

        //test
        val list = viewModel.cartoons
            Log.i("bebra", if (list.isEmpty())"isEmpty" else list[1].toString())

        setContent {
            val navHostController = rememberNavController()
            MainNavHost(navHostController = navHostController, viewModel = viewModel)
        }
    }
}