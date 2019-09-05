package ir.sanam.di

import ir.sanam.AppDispatchers
import ir.sanam.repository.MovieRepo
import ir.sanam.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module.module


val repositoryModule = module {

    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    factory { MovieRepository(get()) as MovieRepo }
}