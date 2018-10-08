package com.example.christoforos.sandbox.presentation.presenters

import com.example.christoforos.sandbox.presentation.screens.MainScreen

class MainPresenter(val screen: MainScreen) : Presenter {

    override fun initialize() {
        test()
    }

    override fun onResume() {
    }

    override fun onDestroy() {
    }

    private fun test() {

    }

    fun loginButtonClicked() {
    }
}