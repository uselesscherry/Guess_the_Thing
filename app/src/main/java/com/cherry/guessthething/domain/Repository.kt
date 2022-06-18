package com.cherry.guessthething.domain

import com.cherry.guessthething.model.Cartoon
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loadCartoonsList()
    suspend fun getCartoons(): List<Cartoon>
    suspend fun saveMaxResult(result:Int)
     fun getMaxResult(): Flow<Int>
}