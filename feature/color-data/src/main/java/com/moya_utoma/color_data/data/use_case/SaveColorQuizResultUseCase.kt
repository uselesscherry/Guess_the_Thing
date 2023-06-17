package com.moya_utoma.color_data.data.use_case

import com.moya_utoma.color_data.data.local.ColorQuizSharedPrefs
import javax.inject.Inject

class SaveColorQuizResultUseCase @Inject constructor(private val colorQuizSharedPrefs: ColorQuizSharedPrefs) {

    suspend operator fun invoke(result: Int) {
        colorQuizSharedPrefs.saveMaxResult(result)
    }

}