package com.dmribeiro87.kaizenapp.core.util

import android.os.CountDownTimer

class SportsCountDownTimer(
    private val startTimeInMillis: Long,
    private val intervalInMillis: Long,
    private val onTick: (Long) -> Unit,
    private val onFinish: () -> Unit
) : CountDownTimerProvider {

    private var countDownTimer: CountDownTimer? = null

    override fun start() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(startTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                onTick.invoke(millisUntilFinished)
            }

            override fun onFinish() {
                onFinish.invoke()
            }
        }.start()
    }

    override fun cancel() {
        countDownTimer?.cancel()
    }
}