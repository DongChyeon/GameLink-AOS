package com.gamelink

import android.app.Application
import com.gamelink.di.dataModule
import com.gamelink.di.networkModule
import com.kakao.sdk.common.KakaoSdk
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
            modules(dataModule)
            modules(dataModule)
        }

        KakaoSdk.init(this, getString(R.string.KAKAO_APP_KEY_FULL))
    }
}