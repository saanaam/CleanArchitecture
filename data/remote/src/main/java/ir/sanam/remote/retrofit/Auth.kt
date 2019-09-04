package ir.sanam.remote.retrofit

import android.content.Context
import android.util.Log

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


//class Auth(private val context: Context) : Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//        if (response.request().header("Authorization") != SharePref.readAccessToken(""))
//            return null
//        val call = adapterRefresh?.
//            create(ApiDriverAcount::class.java)?.
//            DriverRefreshToken(RefreshTokenRequest( SharePref.readRefreshToken("")))
//        try {
//            val responseCall = call?.execute()
//            val data = responseCall?.body()
//            return data?.let {
//                SharePref.writeAccessToken(data.accessToken!!)
//                SharePref.writeRefreshToken(data.refreshToken!!)
//                response.request()
//                    .newBuilder()
//                    .header("Authorization", "Bearer "+ data.accessToken!!)
//                    .build()
//            }
//        } catch (ex: Exception) {
//            Log.d("ERROR", "onResponse: $ex")
//            return null
//        }
//
//    }
//
//    val adapterRefresh: Retrofit? = null
//        get(){
//        if (field == null) {
//            val httpClient = OkHttpClient.Builder()
//            httpClient.addInterceptor(object : Interceptor {
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val request = chain.request()
//                        .newBuilder()
//                        // add Authorization key on request header
//                        // key will be using refresh token
////                        .addHeader("Authorization", SharePref.readRefreshToken(""))
//                        .build()
//                    return chain.proceed(request)
//                }
//            })
//            return Retrofit.Builder()
//                .client(httpClient.build())
//                .baseUrl(AppConstants.url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//        return field
//    }

//}