package com.moya_utoma.color_quiz_ui.util

import androidx.compose.ui.graphics.Color

fun String.parseHexToColor() =
    Color(android.graphics.Color.parseColor("#$this"))