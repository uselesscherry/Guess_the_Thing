package com.moya_utoma.color_data.data.local

import com.moya_utoma.color_data.data.model.NamedColor
import javax.inject.Inject

class ColorsLocalSource @Inject constructor(private val dao: ColorDao) {

    suspend fun getColors(): List<NamedColor> = dao.getColors()

    suspend fun insertAll(colors: List<NamedColor>) {
        dao.insertAll(colors)
    }

}