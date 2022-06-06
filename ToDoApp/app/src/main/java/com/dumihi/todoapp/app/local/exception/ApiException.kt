package com.dumihi.todoapp.app.local.exception


class ApiException @JvmOverloads constructor(
    val code: Int? = null,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {
    companion object {
        fun wrap(message: String?): ApiException {
            return ApiException(message = message)
        }
        fun wrap(code: Int, message: String?): ApiException {
            return ApiException(code, message)
        }

        fun wrap(code: Int, message: String?, cause: Throwable?): ApiException {
            return ApiException(
                code,
                message = message,
                cause = cause
            )
        }
    }
}