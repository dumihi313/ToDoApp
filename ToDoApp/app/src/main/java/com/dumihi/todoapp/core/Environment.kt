package com.dumihi.todoapp.core

import android.content.SharedPreferences
import com.dumihi.todoapp.utils.SingletonHolder
import com.dumihi.todoapp.BuildConfig

private const val PREF_CURRENT_FLAVOR = "pref_key_current_flavor"

private const val PREF_CURRENT_SOCKET = "pref_key_current_socket"

class Environment private constructor(private val storage: SharedPreferences) {
    companion object : SingletonHolder<SharedPreferences, Environment>(::Environment)

    private val flavors = listOf(Flavor.PROD, Flavor.DEV)

    private var currentIndex = 0

    init {
        currentIndex = storage.getInt(PREF_CURRENT_FLAVOR, 0)
    }

    fun rotate() {
        currentIndex = (currentIndex + 1) % flavors.size
        storage.edit().putInt(PREF_CURRENT_FLAVOR, currentIndex).apply()
    }

    fun current(): Flavor {
        return flavors[currentIndex]
    }

    enum class Flavor(
        val apiHost: String,
    ) {
        DEV(BuildConfig.DEV_API_HOST),
        PROD(BuildConfig.PROD_API_HOST);
    }
}