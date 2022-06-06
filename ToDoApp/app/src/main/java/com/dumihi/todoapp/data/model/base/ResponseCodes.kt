package com.dumihi.todoapp.data.model.base

object ResponseCodes {
    private const val SUCCESSFULL = 0
    private const val AUTH_KEY_EXPIRED = -11

    fun isSuccessful(code: Int): Boolean {
        return SUCCESSFULL == code
    }

    fun isSessionExpired(code: Int): Boolean {
        return AUTH_KEY_EXPIRED == code
    }
}