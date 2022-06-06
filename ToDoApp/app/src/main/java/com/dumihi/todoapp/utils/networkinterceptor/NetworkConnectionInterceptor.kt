package com.dumihi.todoapp.utils.networkinterceptor

import com.dumihi.todoapp.utils.NetworkStateManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    private val networkStateManager: NetworkStateManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkStateManager.isAvailable) {
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}