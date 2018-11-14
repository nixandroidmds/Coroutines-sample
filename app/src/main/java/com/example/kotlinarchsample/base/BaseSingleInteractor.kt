package com.example.kotlinarchsample.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseSingleInteractor<P, R> {

    suspend operator fun invoke(params: P): R {
        return withContext(Dispatchers.Default) { run(params) }
    }

    protected abstract suspend fun run(params: P): R
}