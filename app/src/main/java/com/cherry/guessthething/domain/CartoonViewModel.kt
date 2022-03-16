package com.cherry.guessthething.domain

import androidx.lifecycle.ViewModel
import com.cherry.guessthething.data.remote.ResponseService
import com.cherry.guessthething.data.remote.ResponseServiceImpl
import com.cherry.guessthething.model.Cartoon
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.random.Random

class CartoonViewModel(
    responceService: ResponseServiceImpl = ResponseService.create() as ResponseServiceImpl
) : ViewModel() {

var cartoons: ArrayList<Cartoon> = arrayListOf()

    object Setting{
        lateinit var rightAnswer: String
        var roundNumber = 0
    }

    private val url = "https://uaserials.pro/cartoons/"
    private val client = HttpClient(Android)

    suspend fun loadCartoonList(cartoonListCallback: (ArrayList<Cartoon>) -> Unit) {
        var text = ""
        loadHtmlToString { text = it }
        cartoonListCallback(parseHtmlToCartoonList(text))
    }

    private fun parseHtmlToCartoonList(trimmedHtmlCode: String): ArrayList<Cartoon> {
        val imagePattern: Pattern = Pattern.compile("<img src=\"(.*?)\" alt=\"")
        val imageMatcher: Matcher = imagePattern.matcher(trimmedHtmlCode)

        val namePattern: Pattern = Pattern.compile("alt=\"(.*?)\">")
        val nameMatcher: Matcher = namePattern.matcher(trimmedHtmlCode)
        val imgList: ArrayList<String> = arrayListOf()
        val nameList: ArrayList<String> = arrayListOf()
        val fullCartoonList: ArrayList<Cartoon> = arrayListOf()
        while (imageMatcher.find()) {
            imageMatcher.group(1)?.let {
                imgList.add(it)
            }
        }
        while (nameMatcher.find()) {
            nameMatcher.group(1)?.let {
                nameList.add(it)
            }
        }
        for (i in 0 until imgList.size) {
            fullCartoonList.add(Cartoon(imgList[i], nameList[i]))
        }
        cartoons.addAll(fullCartoonList)
        return fullCartoonList
    }


    private suspend fun loadHtmlToString(html: (String) -> Unit) {

        val response: HttpResponse = client.get(url) {
            method = HttpMethod.Get
        }
        val result: String = response.receive()

        client.close()
        html(result.trimHtmlCode())
    }

    private fun String.trimHtmlCode(): String {
        return this.substringAfter("sect-content").substringBefore("bottom-nav clr ignore-select")
    }

    fun getNewQuiz(cartoons:ArrayList<Cartoon>,randomCartoon:(Cartoon)->Unit){
        val answerArrayIndex = Random.nextInt(0,cartoons.size)
        var cartoon = cartoons[answerArrayIndex]

    }


}