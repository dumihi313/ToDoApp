package com.dumihi.todoapp.utils.networkinterceptor

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}