package com.dongchyeon.di

import com.dongchyeon.datasource.AuthDataSource
import com.dongchyeon.datasource.AuthDataSourceImpl
import com.dongchyeon.datasource.ChatDataSource
import com.dongchyeon.datasource.ChatDataSourceImpl
import com.dongchyeon.datasource.ProfileDataSource
import com.dongchyeon.datasource.ProfileDataSourceImpl
import com.dongchyeon.datasource.UserTokenDataSource
import com.dongchyeon.datasource.UserTokenDataSourceImpl
import com.dongchyeon.repository.AuthRepository
import com.dongchyeon.repository.AuthRepositoryImpl
import com.dongchyeon.repository.ChatRepository
import com.dongchyeon.repository.ChatRepositoryImpl
import com.dongchyeon.repository.ProfileRepository
import com.dongchyeon.repository.ProfileRepositoryImpl
import com.dongchyeon.repository.UserTokenRepository
import com.dongchyeon.repository.UserTokenRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<AuthDataSource> { AuthDataSourceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ChatDataSource> { ChatDataSourceImpl(get()) }
    single<ChatRepository> { ChatRepositoryImpl(get()) }
    single<ProfileDataSource> { ProfileDataSourceImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single<UserTokenDataSource> { UserTokenDataSourceImpl(get()) }
    single<UserTokenRepository> { UserTokenRepositoryImpl(get()) }
}