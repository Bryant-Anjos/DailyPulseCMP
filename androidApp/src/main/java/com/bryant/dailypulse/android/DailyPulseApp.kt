package com.bryant.dailypulse.android

import android.app.Application
import com.bryant.dailypulse.android.di.databaseModule
import com.bryant.dailypulse.android.di.viewModelsModule
import com.bryant.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule + databaseModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}