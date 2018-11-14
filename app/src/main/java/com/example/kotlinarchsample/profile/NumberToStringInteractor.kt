package com.example.kotlinarchsample.profile

import com.example.kotlinarchsample.base.BaseSingleInteractor
import kotlinx.coroutines.delay

class NumberToStringInteractor : BaseSingleInteractor<Int, String>() {

    override suspend fun run(params: Int): String {
        if (params < 0) {
            throw IllegalArgumentException("Can't process negative values")
        }
        delay(1000)
        return "Number is $params"
    }
}