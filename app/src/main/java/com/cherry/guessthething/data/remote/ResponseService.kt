package com.cherry.guessthething.data.remote

interface ResponseService {
    suspend fun getPosts(): String
}