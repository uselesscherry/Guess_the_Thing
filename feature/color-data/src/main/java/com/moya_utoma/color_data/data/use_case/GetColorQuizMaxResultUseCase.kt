package com.moya_utoma.color_data.data.use_case

import com.moya_utoma.color_data.data.local.ColorQuizSharedPrefs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetColorQuizMaxResultUseCase @Inject constructor(
    private val quizSharedPrefs: ColorQuizSharedPrefs
) {

    operator fun invoke(): Flow<Int> = quizSharedPrefs.getMaxResult()

}