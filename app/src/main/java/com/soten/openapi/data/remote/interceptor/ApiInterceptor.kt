package com.soten.openapi.data.remote.interceptor

import com.soten.openapi.data.remote.api.ApiInfo
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader(APP_KEY, ApiInfo.KEY)
            .build()
        proceed(newRequest)
    }

    companion object {
        private const val APP_KEY = "appKey"
    }
}