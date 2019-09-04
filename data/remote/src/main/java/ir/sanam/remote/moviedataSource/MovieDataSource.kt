package ir.sanam.remote.moviedataSource

import ir.sanam.remote.api.MovieApi

class MovieDataSource(private val movieApi: MovieApi){
 fun getMovieList() =movieApi.GetMovieList()
}