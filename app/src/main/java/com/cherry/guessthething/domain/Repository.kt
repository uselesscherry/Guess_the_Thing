package com.cherry.guessthething.domain

import com.cherry.guessthething.model.Cartoon

interface Repository {
    suspend fun loadCartoonsList()
    suspend fun getCartoons(): List<Cartoon>
    suspend fun saveMaxResult(result:Int)
    suspend fun getMaxResult(): Int
}