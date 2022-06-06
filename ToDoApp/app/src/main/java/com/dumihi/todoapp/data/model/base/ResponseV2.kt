package com.dumihi.todoapp.data.model.base

import com.google.gson.annotations.SerializedName


open class ResponseV2<T> : BaseResponse<T>() {
    override val isSuccess: Boolean
        get() = ResponseCodes.isSuccessful(code)

    @SerializedName("error")
    private val code = 0

    @SerializedName("message")
    private val message: String? = null

    @SerializedName("data")
    private val data: T? = null

    override fun getData(): T? {
      return data
    }
}

