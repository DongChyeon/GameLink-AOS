package com.gamelink.di

import com.gamelink.datasource.AuthDataSource
import com.gamelink.datasource.AuthDataSourceImpl
import com.gamelink.datasource.ChatDataSource
import com.gamelink.datasource.ChatDataSourceImpl
import com.gamelink.datasource.ProfileDataSource
import com.gamelink.datasource.ProfileDataSourceImpl
import com.gamelink.datasource.UserTokenDataSource
import com.gamelink.datasource.UserTokenDataSourceImpl
import com.gamelink.repository.AuthRepository
import com.gamelink.repository.AuthRepositoryImpl
import com.gamelink.repository.ChatRepository
import com.gamelink.repository.ChatRepositoryImpl
import com.gamelink.repository.ProfileRepository
import com.gamelink.repository.ProfileRepositoryImpl
import com.gamelink.repository.UserTokenRepository
import com.gamelink.repository.UserTokenRepositoryImpl
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