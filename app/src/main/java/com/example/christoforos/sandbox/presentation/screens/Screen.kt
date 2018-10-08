package com.example.christoforos.sandbox.presentation.screens

import android.content.Context

interface Screen {
    val appContext: Context
    val screenContext: Context
    fun showLoading()
    fun hideLoading()
    fun showError()
}