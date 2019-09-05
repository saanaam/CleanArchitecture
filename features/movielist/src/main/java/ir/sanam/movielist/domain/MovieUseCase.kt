package ir.sanam.movielist.domain

import ir.sanam.repository.MovieRepo


class MovieUseCase(private val repository: MovieRepo) {

    suspend operator fun invoke()= repository.GetMovieList()
}
