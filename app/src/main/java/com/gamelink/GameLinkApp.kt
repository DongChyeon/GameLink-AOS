package com.gamelink

import android.app.Application
import com.gamelink.di.dataModule
import com.gamelink.di.networkModule
import com.gamelink.feature.auth.authViewModelModule
import com.kakao.sdk.common.KakaoSdk
import org.koin.core.context.startKoin

class GameLinkApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule)
            modules(dataModule)
            modules(authViewModelModule)
        }

        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}