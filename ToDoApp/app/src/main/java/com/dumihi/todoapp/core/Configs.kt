package com.dumihi.todoapp.core

import com.dumihi.todoapp.BuildConfig

data class Configs(
    val currentVersion: String,
    val lowestVersion: String,
) {
    companion object {
        @JvmStatic
        val DEFAULT = Configs(
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_NAME,
        )
    }
}
