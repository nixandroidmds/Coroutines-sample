package com.example.kotlinarchsample.profile

import com.example.kotlinarchsample.base.BasePublisherInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay

class PublisherNumbersInteractor : BasePublisherInteractor<Int, Int>() {

    override suspend fun CoroutineScope.run(params: Int, channel: SendChannel<Int>) {
        for (i in 0..params) {
            channel.send(i)
            delay(1000)
        }
    }
}