package com.example.christoforos.sandbox.presentation.utils

class Timer {
    private var listener: OnTickListener? = null
    private var task: (() -> Unit)? = null
    private val futureTask: FutureTask
    private var interval: Long? = null
    private var stopped: Boolean = false

    constructor(listener: OnTickListener) {
        this.listener = listener
        this.task = null
    }

    constructor(task: () -> Unit) {
        this.listener = null
        this.task = task
    }

    init {
        futureTask = FutureTask {
            listener?.onTick()
            task?.invoke()
            if (!stopped)
                start(interval)
        }
    }

    fun startNow(interval: Long? = null) {
        listener?.onTick()
        task?.invoke()
        start(interval)
    }

    fun start(interval: Long? = null) {
        stopped = false
        interval?.let { this.interval = it }
        this.interval?.let { futureTask.start(it) }
    }

    fun stop() {
        stopped = true
        futureTask.cancel()
    }

    interface OnTickListener {
        fun onTick()
    }
}