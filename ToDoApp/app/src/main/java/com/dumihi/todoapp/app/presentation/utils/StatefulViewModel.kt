package com.dumihi.todoapp.app.presentation.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dumihi.todoapp.utils.TodoEvent

abstract class StatefulViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<TodoEvent<Boolean>>()

    val isLoading: LiveData<TodoEvent<Boolean>>
        get() = _isLoading

    private val _error = MutableLiveData<TodoEvent<Throwable>>()

    val error: LiveData<TodoEvent<Throwable>>
        get() = _error

    @MainThread
    protected fun setLoading(loading: Boolean) {
        _isLoading.postValue(TodoEvent(loading))
    }

    @MainThread
    protected fun setError(error: Throwable) {
        _error.postValue(TodoEvent(error))
    }
}