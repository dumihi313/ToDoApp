package com.dumihi.todoapp.data.model

sealed class TodoResult<out R> {
    data class Success<out T>(val data: T?) : TodoResult<T>()
    data class Error(val throwable: Throwable) : TodoResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
        }
    }
}

val TodoResult<*>.succeeded
    get() = this is TodoResult.Success && data != null

val <T> TodoResult<T>.data: T?
    get() = (this as? TodoResult.Success)?.data

fun <T> TodoResult<T>.successOr(fallback: T): T {
    return (this as? TodoResult.Success<T>)?.data ?: fallback
}

fun <T, R> TodoResult<T>.map(transform: (T?) -> R): TodoResult<R> {
    return when (this) {
        is TodoResult.Success -> TodoResult.Success(transform(data))
        is TodoResult.Error -> TodoResult.Error(throwable)
    }
}