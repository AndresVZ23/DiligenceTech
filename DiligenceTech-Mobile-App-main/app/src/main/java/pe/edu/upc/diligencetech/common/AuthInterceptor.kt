package pe.edu.upc.diligencetech.common

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: () -> String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = tokenProvider()
        val requestWithAuth = original.newBuilder()
            .apply {
                token?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }
            .build()
        Log.d("AuthInterceptor", "$requestWithAuth")
        return chain.proceed(requestWithAuth)
    }
}