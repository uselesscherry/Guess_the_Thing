package com.moya_utoma.color_data.data.use_case

import com.moya_utoma.color_data.data.local.ColorsLocalSource
import com.moya_utoma.color_data.data.remote.ColorsNetSource
import javax.inject.Inject

class DownloadColorsUseCase @Inject constructor(
    private val colorsNetSource: ColorsNetSource,
    private val colorsLocalSource: ColorsLocalSource
) {

    suspend operator fun invoke() {
        val colors = colorsNetSource.getColors()
        colorsLocalSource.insertAll(colors)
    }

}