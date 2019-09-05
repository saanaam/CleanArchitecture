package ir.sanam.movielist.di

import ir.sanam.movielist.domain.MovieUseCase
import ir.sanam.movielist.ui.MovieViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val movieListModule = module {
    factory { MovieUseCase(get()) }
    viewModel { MovieViewModel(get() , get()) }
}