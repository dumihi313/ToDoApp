package com.dumihi.todoapp.data.model.base

import com.google.gson.annotations.SerializedName


abstract class ResponseV3<T> : BaseResponse<T>() {
    override val isSuccess: Boolean
        get() = status.isSuccessful

    val isSessionExpired: Boolean
        get() = status.isSessionExpired

    fun getMessage(): String {
        return status.message
    }

    @SerializedName("status")
    lateinit var status: StatusData
        private set

    class StatusData(
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
    ) {
        val isSuccessful: Boolean
            get() = ResponseCodes.isSuccessful(code)
        val isSessionExpired: Boolean
            get() = ResponseCodes.isSessionExpired(code)
    }
}

