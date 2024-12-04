package com.gamelink

import android.app.Application
import com.daon.feature.chat.chatViewModelModule
import com.dongchyeon.feature.profile.profileViewModelModule
import com.gamelink.di.dataModule
import com.gamelink.di.dataStoreModule
import com.gamelink.di.networkModule
import com.gamelink.feature.auth.authViewModelModule
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GameLinkApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GameLinkApp)
            modules(networkModule)
            modules(dataModule)
            modules(dataStoreModule)
            modules(authViewModelModule)
            modules(chatViewModelModule)
            modules(profileViewModelModule)
        }
        FirebaseApp.initializeApp(this)
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}