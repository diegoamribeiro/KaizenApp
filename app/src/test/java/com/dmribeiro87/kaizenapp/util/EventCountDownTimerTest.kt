package com.dmribeiro87.kaizenapp.util

import com.dmribeiro87.kaizenapp.core.util.EventCountDownTimer
import org.junit.Assert.*
import org.junit.Test


class EventCountDownTimerTest {

    @Test
    fun testCountDownTimer() {
        var tickCalled = false
        var finishCalled = false

        val mockCountDownTimer = MockCountDownTimer(
            totalTimeInMillis = 2000,
            intervalInMillis = 1000,
            onTick = { millisUntilFinished ->
                tickCalled = true
                assertEquals(1000, millisUntilFinished)
            },
            onFinish = {
                finishCalled = true
            }
        )

        val eventCountDownTimer = EventCountDownTimer(mockCountDownTimer)
        eventCountDownTimer.start()

        // Simulate the passage of time
        Thread.sleep(3000)

        assertTrue(tickCalled)
        assertTrue(finishCalled)
    }
}