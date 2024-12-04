package com.dongchyeon

import android.app.Application
import com.dongchyeon.feature.chat.chatViewModelModule
import com.dongchyeon.feature.profile.profileViewModelModule
import com.dongchyeon.di.dataModule
import com.dongchyeon.di.dataStoreModule
import com.dongchyeon.di.networkModule
import com.dongchyeon.feature.auth.authViewModelModule
import com.gamelink.BuildConfig
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