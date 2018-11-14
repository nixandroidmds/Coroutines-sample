package com.example.kotlinarchsample.base

import android.support.annotation.CallSuper
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<V : View> : CoroutineScope {

    protected var view: V? = null
        private set

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @CallSuper
    open fun onAttach(view: V) {
        this.view = view
    }

    @CallSuper
    open fun onDetach() {
        job.cancel()
        view = null
    }
}

