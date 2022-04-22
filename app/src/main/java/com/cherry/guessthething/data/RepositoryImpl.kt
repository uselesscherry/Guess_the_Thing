package com.cherry.guessthething.data

import android.app.Application
import com.cherry.guessthething.data.local.CartoonDao
import com.cherry.guessthething.data.local.CartoonDatabase
import com.cherry.guessthething.data.remote.ResponseService
import com.cherry.guessthething.data.remote.ResponseServiceImpl
import com.cherry.guessthething.domain.Repository
import com.cherry.guessthething.domain.parseHtmlToCartoonList
import com.cherry.guessthething.model.Cartoon

class RepositoryImpl(
    application: Application,
    private val responseService: ResponseService = ResponseService.create() as ResponseServiceImpl,
private val cartoonDao: CartoonDao = CartoonDatabase.getInstance(application).cartoonDao()
):Repository {

    private var cartoonsList:List<Cartoon> = emptyList()

    override suspend fun loadCartoonsList() {
        cartoonsList = parseHtmlToCartoonList(trimmedHtmlCode = responseService.getPosts())
     insertToDb()
    }

     private suspend fun insertToDb() {
         if (cartoonDao.getCartoons().containsAll(cartoonsList)){
             return
         } else{
             cartoonDao.insertAllCartoons(cartoonsList)
         }
    }

    override suspend fun getCartoons(): List<Cartoon> = cartoonDao.getCartoons()

}