package ir.sanam.remote.di


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import ir.sanam.remote.api.MovieApi
import ir.sanam.remote.moviedataSource.MovieDataSource
import ir.sanam.remote.retrofit.HeaderInterceptor
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun createRemoteModule(baseUrl: String) = module {
    val MOVIE_RETROFIT = "MOVIE_RETROFIT"
    val MOVIE_CLIENT = "MOVIE_CLIENT"


    factory<Interceptor>("log") {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory(name = MOVIE_CLIENT) { OkHttpClient.Builder()
        .authenticator(get())
        .addInterceptor(get("log"))
        .addInterceptor(get<HeaderInterceptor>() ).build()

    }

    single(name= MOVIE_RETROFIT) {
        Retrofit.Builder()
            .client(get(name = MOVIE_CLIENT))
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { get<Retrofit>(name= MOVIE_RETROFIT).create(MovieApi::class.java) }


    factory { MovieDataSource(get()) }

//    factory<Authenticator> { Auth(androidContext()) }
//    factory { HeaderInterceptor() }


}