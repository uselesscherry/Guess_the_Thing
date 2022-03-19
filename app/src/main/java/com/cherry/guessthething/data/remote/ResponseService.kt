package com.cherry.guessthething.data.remote

import io.ktor.client.*
import io.ktor.client.engine.android.*

interface ResponseService {
    suspend fun getPosts(): String

    companion object {
        fun create(): ResponseService {
            return ResponseServiceImpl(
                client = HttpClient(Android) {
                }
            )
        }
    }
}