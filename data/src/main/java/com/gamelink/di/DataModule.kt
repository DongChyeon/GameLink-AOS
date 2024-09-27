package com.gamelink.di

import com.gamelink.datasource.AuthDataSource
import com.gamelink.datasource.AuthDataSourceImpl
import org.koin.dsl.module
import com.gamelink.repository.AuthRepository
import com.gamelink.repository.AuthRepositoryImpl

val dataModule = module {
    single<AuthDataSource> { AuthDataSourceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}