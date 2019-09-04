package ir.sanam.cleanarchitecture

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import ir.sanam.cleanarchitecture.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


open class App: Application() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


    override fun onCreate() {
        super.onCreate()
        configureDi()

    }


    // CONFIGURATION ---
    open fun configureDi() =
        startKoin { androidContext(this@App) ; provideComponent()}

    // PUBLIC API ---
    open fun provideComponent() = appComponent
}