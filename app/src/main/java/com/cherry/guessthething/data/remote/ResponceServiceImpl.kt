package com.cherry.guessthething.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ResponseServiceImpl(
    private val client: HttpClient
) : ResponseService {
    override suspend fun getPosts(): String {
        return try {
            val response: HttpResponse = client.get("https://uaserials.pro/cartoons/") {
                method = HttpMethod.Get
            }
            response.receive()

        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            "null"
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            "null"
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            "null"
        } catch (e: Exception) {
            println("Error: ${e.message}")
            "null"
        } finally {
            client.close()
        }
    }
}