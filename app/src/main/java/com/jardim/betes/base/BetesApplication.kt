package com.jardim.betes.base

import android.app.Application
import com.jardim.betes.di.repositoryModules
import com.jardim.betes.di.viewModelModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class BetesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        Hawk.init(this).build()

        startKoin {
            androidLogger()
            androidContext(this@BetesApplication)
            modules(listOf(viewModelModule, repositoryModules))
        }
    }
}