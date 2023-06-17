package com.moya_utoma.color_data.data.remote

import com.moya_utoma.color_data.data.model.NamedColor
import javax.inject.Inject

class ColorsNetSource @Inject constructor(private val apiService: ColorApiService) {
    suspend fun getColors(): List<NamedColor> = apiService.getColors()
}