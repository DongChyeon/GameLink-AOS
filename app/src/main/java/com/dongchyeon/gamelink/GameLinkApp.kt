package com.dongchyeon.gamelink

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
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

        KakaoSdk.init(this, getString(R.string.KAKAO_APP_KEY_FULL))
    }
}