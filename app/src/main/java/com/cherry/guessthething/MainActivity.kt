package com.cherry.guessthething

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.cherry.guessthething.domain.CartoonViewModel
import com.cherry.guessthething.model.Cartoon
import com.cherry.guessthething.ui.theme.GuessTheThingTheme
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {

    private val viewmodel = CartoonViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var list: ArrayList<Cartoon> = arrayListOf()
        lifecycleScope.launch {

            viewmodel.loadCartoonList {
                list=it
            }
            Log.i("bebra", list[1].toString())
        }
        setContent {
            var texty by remember {
                mutableStateOf("bebra")
            }
            GuessTheThingTheme {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = texty)
                }


            }
        }
    }
}
