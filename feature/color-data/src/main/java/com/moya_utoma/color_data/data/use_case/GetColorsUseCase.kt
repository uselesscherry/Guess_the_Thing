package com.moya_utoma.color_data.data.use_case

import com.moya_utoma.color_data.data.local.ColorsLocalSource
import com.moya_utoma.color_data.data.model.NamedColor
import javax.inject.Inject

class GetColorsUseCase @Inject constructor(private val colorsLocalSource: ColorsLocalSource) {

    suspend operator fun invoke(): List<NamedColor> {
        return colorsLocalSource.getColors()
    }

}