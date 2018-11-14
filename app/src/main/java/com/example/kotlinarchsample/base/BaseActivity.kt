package com.example.kotlinarchsample.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter<V>, V:View> : AppCompatActivity(), View {

    protected abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }


}