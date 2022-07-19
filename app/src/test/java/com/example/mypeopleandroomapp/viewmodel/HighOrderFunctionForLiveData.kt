package com.example.mypeopleandroomapp.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun<T> LiveData<T>.fetchOrAwait(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserver: ()->Unit = {}
):T {
    var info: T? = null
    val countLatch = CountDownLatch(1)
    val instanceObserver = object: Observer<T>{
        override fun onChanged(t: T) {
            info = t
            countLatch.countDown()
            this@fetchOrAwait.removeObserver(this)
        }
    }
    this.observeForever(instanceObserver)
    try {
        afterObserver.invoke()
        if(!countLatch.await(time, timeUnit)) {
            throw TimeoutException("Value of the Live data is not initialised")
        }
    }
    finally {
        this.removeObserver(instanceObserver)
    }
    return info as T
}