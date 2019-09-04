package ir.sanam.cleanarchitecture.di

import ir.sanam.remote.di.createRemoteModule

val appComponent = listOf(
    createRemoteModule("http://www.omdbapi.com")
)