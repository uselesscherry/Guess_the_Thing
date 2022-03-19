package com.cherry.guessthething.domain

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherry.guessthething.data.remote.ResponseService
import com.cherry.guessthething.data.remote.ResponseServiceImpl
import com.cherry.guessthething.model.Cartoon
import com.cherry.guessthething.util.parseHtmlToCartoonList
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartoonViewModel(
    private val responseService
    : ResponseServiceImpl = ResponseService.create() as ResponseServiceImpl
) : ViewModel() {

    var cartoons: ArrayList<Cartoon> = arrayListOf()
    private val buttonCount = 4
    val isLoaded: Boolean
    get() = cartoons.isNotEmpty()

    private object Setting {
        lateinit var rightAnswer: String
        var rightAnswerPosition: Int = 0
    }

    init {

        Log.i("bebra", isLoaded.toString())
        viewModelScope.launch {
            cartoons = loadCartoonList()

            Log.i("bebra", isLoaded.toString())
        }
    }


    private suspend fun loadCartoonList(): ArrayList<Cartoon> {
        val text = responseService.getPosts()
        return parseHtmlToCartoonList(text)
    }


    fun playGame() {

    }

    private fun generateQuestion() {
        Setting.rightAnswerPosition = Random.nextInt(buttonCount)
    }

}