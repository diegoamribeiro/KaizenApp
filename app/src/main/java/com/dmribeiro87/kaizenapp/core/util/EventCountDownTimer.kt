package com.dmribeiro87.kaizenapp.core.util

class EventCountDownTimer(
    private val countDownTimerProvider: CountDownTimerProvider
) {

    fun start() {
        countDownTimerProvider.start()
    }

    fun cancel() {
        countDownTimerProvider.cancel()
    }
}