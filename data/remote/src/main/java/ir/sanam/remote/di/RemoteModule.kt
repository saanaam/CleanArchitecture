package ir.sanam.remote.di

import org.koin.dsl.module
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor



fun createRemoteModule() = module {
    val MOVIE_RETROFIT = "MOVIE_RETROFIT"
    val MOVIE_CLIENT = "MOVIE_CLIENT"


    factory<Interceptor>("log") {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }


}