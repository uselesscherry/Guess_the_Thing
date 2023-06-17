package com.moya_utoma.color_data.data.remote

import com.moya_utoma.color_data.data.model.NamedColor
import retrofit2.http.GET

interface ColorApiService {

    @GET("/fresh/json/")
    suspend fun getColors(): List<NamedColor>

}