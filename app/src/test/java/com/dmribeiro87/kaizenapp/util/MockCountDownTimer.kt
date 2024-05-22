package com.dmribeiro87.kaizenapp.util

import com.dmribeiro87.kaizenapp.core.util.CountDownTimerProvider

class MockCountDownTimer(
    private val totalTimeInMillis: Long,
    private val intervalInMillis: Long,
    private val onTick: (Long) -> Unit,
    private val onFinish: () -> Unit
) : CountDownTimerProvider {

    private var remainingTimeInMillis = totalTimeInMillis
    private var isRunning = false

    override fun start() {
        isRunning = true
        Thread {
            while (isRunning && remainingTimeInMillis > 0) {
                Thread.sleep(intervalInMillis)
                remainingTimeInMillis -= intervalInMillis
                if (remainingTimeInMillis > 0) {
                    onTick.invoke(remainingTimeInMillis)
                }
            }
            if (isRunning) {
                onFinish.invoke()
            }
        }.start()
    }

    override fun cancel() {
        isRunning = false
    }
}