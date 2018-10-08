package com.example.christoforos.sandbox.presentation.utils

import android.os.Handler

class FutureTask(private val task: () -> Unit) {
    private val handler = Handler()
    private val runnable = Runnable { task.invoke() }

    fun start(delay: Long) {
        handler.postDelayed(runnable, delay)
    }

    fun cancel() {
        handler.removeCallbacks(runnable)
    }
}
