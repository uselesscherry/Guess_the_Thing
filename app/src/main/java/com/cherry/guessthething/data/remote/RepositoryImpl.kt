package com.cherry.guessthething.data.remote

import com.cherry.guessthething.domain.Repository
import com.cherry.guessthething.domain.parseHtmlToCartoonList
import com.cherry.guessthething.model.Cartoon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val responseService: ResponseService = ResponseService.create() as ResponseServiceImpl
):Repository {

    var cartoonsList:List<Cartoon> = emptyList()
    private set
    override suspend fun downloadCartoonsList() {
        cartoonsList = parseHtmlToCartoonList(trimmedHtmlCode = responseService.getPosts())
    }

   /* override suspend fun insertToDb(list: List<Cartoon>) {
        TODO("Not yet implemented")
    }

    override suspend fun getCartoons(): List<Cartoon> {
        TODO("Not yet implemented")
    }*/
}