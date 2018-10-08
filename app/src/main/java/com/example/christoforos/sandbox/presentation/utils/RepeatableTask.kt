package com.example.christoforos.sandbox.presentation.utils

class RepeatableTask(private val task: () -> Unit) {
    private lateinit var timer: Timer
    private var currentRepeat: Int = 0
    var listener: RepeatableTaskListener? = null

    fun start(interval: Long, repeat: Int = 0) {
        timer = Timer {
            if (repeat == 0 || currentRepeat <= repeat) {
                task.invoke()
                listener?.onNext(currentRepeat)
                currentRepeat++
            }

            if (repeat != 0 && currentRepeat > repeat) {
                timer.stop()
                listener?.onComplete()
            }
        }

        timer.startNow(interval)
    }

    fun resume() {
        timer.startNow()
    }

    fun pause() {
        timer.stop()
    }

    fun stop() {
        timer.stop()
        currentRepeat = 0
    }

    interface RepeatableTaskListener {
        fun onNext(repeat: Int) {}
        fun onComplete() {}
    }
}