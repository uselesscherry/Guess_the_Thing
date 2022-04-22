package com.cherry.guessthething.domain

import com.cherry.guessthething.model.Cartoon

interface Repository {
    suspend fun downloadCartoonsList()
    /*suspend fun insertToDb(list: List<Cartoon>)
    suspend fun getCartoons():List<Cartoon>*/
}