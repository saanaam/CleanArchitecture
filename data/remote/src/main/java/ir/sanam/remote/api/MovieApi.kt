package ir.sanam.remote.api

import ir.sanam.model.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi{
    @FormUrlEncoded
    @GET()
    fun GetMovieList(@Query("apikey") apikey: String  = "3e974fca", @Query("s") s: String = "batman"): Deferred<MovieResponse>
}