package com.dumihi.todoapp.data.model.base

abstract class BaseResponse<T> {
    abstract val isSuccess: Boolean
    abstract fun getData() : T?
}