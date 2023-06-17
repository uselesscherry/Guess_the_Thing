package com.moya_utoma.quizer.timer

import kotlinx.coroutines.delay

suspend fun countDown(time: Int, onCountEnd: suspend () -> Unit) {
    if (time > 0) {
        var funTime = time
        while (funTime != 0) {
            funTime--
            delay(1000)
        }
        onCountEnd()
    }
}