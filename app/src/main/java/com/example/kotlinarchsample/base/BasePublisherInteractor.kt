package com.example.kotlinarchsample.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch

abstract class BasePublisherInteractor<P, R> {

    operator fun invoke(params: P, scope: CoroutineScope): ReceiveChannel<R> {
        val channel = Channel<R>()

        scope.launch(Dispatchers.Default) {
            try {
                run(params, channel)
                channel.close()
            } catch (t: Throwable) {
                channel.close(t)
            }
        }
        return channel
    }

    protected abstract suspend fun CoroutineScope.run(params: P, channel: SendChannel<R>)
}