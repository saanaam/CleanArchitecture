package ir.sanam.cleanarchitecture.di

import ir.sanam.di.repositoryModule
import ir.sanam.remote.di.createRemoteModule

val appComponent = listOf(
    createRemoteModule("http://www.omdbapi.com"),
    repositoryModule
)