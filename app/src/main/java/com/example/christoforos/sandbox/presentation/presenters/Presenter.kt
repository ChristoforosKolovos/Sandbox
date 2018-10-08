package com.example.christoforos.sandbox.presentation.presenters

interface Presenter {
    fun initialize()
    fun onResume()
    fun onDestroy()
}