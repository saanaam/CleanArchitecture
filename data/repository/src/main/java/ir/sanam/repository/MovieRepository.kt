package ir.sanam.repository

import androidx.lifecycle.LiveData
import ir.sanam.model.MovieResponse
import ir.sanam.remote.moviedataSource.MovieDataSource
import ir.sanam.utils.RemoteBoundResourceLiveData
import ir.sanam.utils.Resource
import kotlinx.coroutines.Deferred

interface MovieRepo{
fun GetMovieList() : LiveData<Resource<MovieResponse>>
}

class MovieRepository(private val movieDataSource: MovieDataSource) : MovieRepo{
    override fun GetMovieList(): LiveData<Resource<MovieResponse>> {

        return  object : RemoteBoundResourceLiveData<MovieResponse, MovieResponse>() {


            override fun processResponse(response: MovieResponse): MovieResponse =
                MovieResponse(response.movieItems  , response.totalResults , response.response)


            override suspend fun saveInDatabase(data: MovieResponse) {}

            override suspend fun baseProcessDatabase() {}

            override suspend fun createCallAsync(): Deferred<MovieResponse> = movieDataSource.getMovieList()

        }.result


    }

}