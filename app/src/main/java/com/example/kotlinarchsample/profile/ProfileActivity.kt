package com.example.kotlinarchsample.profile

import android.os.Bundle
import android.widget.Toast
import com.example.kotlinarchsample.R
import com.example.kotlinarchsample.base.BaseActivity

class ProfileActivity : BaseActivity<ProfilePresenter, ProfileView>(), ProfileView {

    //could be injected with DI
    private val profilePresenter = ProfilePresenter()

    override val presenter: ProfilePresenter
        get() = profilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
