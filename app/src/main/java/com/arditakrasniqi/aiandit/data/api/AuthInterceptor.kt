package com.arditakrasniqi.aiandit.data.api

import com.arditakrasniqi.aiandit.presentation.utils.AppSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPreferences: AppSharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.addHeader("X-UA-Platform", "android")
        requestBuilder.addHeader("Content-Type", "application/json")
        sharedPreferences.token?.let { requestBuilder.addHeader("Authorization", it) }
        val newRequest = requestBuilder.build()
        val response = chain.proceed(newRequest)
        if (response.code == 401) {
            sharedPreferences.deletePrefs()
        }
        return response
    }
}