package com.dumihi.todoapp.app.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface NavHost {
    @IdRes
    fun containerId(): Int

    fun navHostFragmentManager(): FragmentManager

    fun containerView(): View
}