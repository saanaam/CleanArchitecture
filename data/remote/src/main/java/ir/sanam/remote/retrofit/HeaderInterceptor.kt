package ir.sanam.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
//            .addHeader("Authorization", SharePref.readAccessToken("")!!)
//            .addHeader("lang", SharePref.readlanguage("en")!!)
            .build()
        return chain.proceed(request)
    }
}