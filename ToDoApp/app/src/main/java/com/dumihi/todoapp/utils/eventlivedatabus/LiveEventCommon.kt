package com.dumihi.todoapp.utils.eventlivedatabus

import androidx.lifecycle.BusLiveData

interface LiveEventCommon {
    fun backFromEmptyFragmentListener(): BusLiveData<Unit>
}