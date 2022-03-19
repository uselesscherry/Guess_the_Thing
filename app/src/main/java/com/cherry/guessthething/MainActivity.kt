package com.cherry.guessthething

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.cherry.guessthething.domain.CartoonViewModel
import com.cherry.guessthething.model.Cartoon
import com.cherry.guessthething.view.StartScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel:CartoonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = CartoonViewModel()
        var list: ArrayList<Cartoon>
        // lifecycleScope.launch {

            list = viewModel.cartoons
            Log.i("bebra", if (list.isEmpty())"isEmpty" else list[1].toString())
      //  }
        setContent {
StartScreen(viewModel = viewModel)
        }
    }
}