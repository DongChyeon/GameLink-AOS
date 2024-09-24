package com.dongchyeon.gamelink

import android.app.Application
import di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GameLinkApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GameLinkApp)
            modules(networkModule)
        }
    }
}