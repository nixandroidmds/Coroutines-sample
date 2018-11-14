package com.example.kotlinarchsample.profile

import com.example.kotlinarchsample.base.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.map
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@ObsoleteCoroutinesApi
class ProfilePresenter : BasePresenter<ProfileView>() {

    private val publisherNumbersInteractor = PublisherNumbersInteractor()
    private val numberToStringInteractor = NumberToStringInteractor()

    override fun onAttach(view: ProfileView) {
        super.onAttach(view)
        launch {
            val channel = publisherNumbersInteractor(5, this)
            channel.consumeEach {
                try {
                    val string = numberToStringInteractor(it)
                    view.showToast(string)
                } catch (e: IllegalArgumentException) {
                    view.showToast("Error: ${e.message}")
                }
            }
            view.showToast("All numbers processed")
        }
    }

    suspend fun <A, B, C> CoroutineScope.combineLatest(
            channelA: ReceiveChannel<A>,
            channelB: ReceiveChannel<B>,
            combiner: (a: A, b: B) -> C)
            : ReceiveChannel<C> {
        return produce {
            var lastA: A? = null
            var lastB: B? = null
            val jobA = launch {
                channelA.consumeEach {
                    lastA = it
                    if (lastB != null) {
                        val combinedValue = combiner(it, lastB!!)
                        send(combinedValue)
                    }
                }
            }
            val jobB = launch {
                channelB.consumeEach {
                    lastB = it
                    if (lastA != null) {
                        val combinedValue = combiner(lastA!!, it)
                        send(combinedValue)
                    }
                }
            }
            joinAll(jobA, jobB)
        }
    }
}