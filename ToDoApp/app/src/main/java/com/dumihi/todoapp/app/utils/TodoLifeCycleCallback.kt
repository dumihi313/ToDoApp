package com.dumihi.todoapp.app.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class TodoLifeCycleCallback @Inject constructor(
    private val connectionManager: ConnectionManager
) : Application.ActivityLifecycleCallbacks {
    private val foregroundActivitiesCount: AtomicInteger = AtomicInteger(0)

    private val metamonActivitiesCount: AtomicInteger = AtomicInteger(0)

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        metamonActivitiesCount.incrementAndGet()
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        val activitiesCount = metamonActivitiesCount.decrementAndGet()
        if (activitiesCount == 0) {
            onApplicationDestroyed()
        }
    }

    private fun onApplicationDestroyed() {
        GlobalScope.launch {
            connectionManager.disconnect()
        }
    }
}