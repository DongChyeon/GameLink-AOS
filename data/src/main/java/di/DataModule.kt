package di

import datasource.AuthDataSource
import datasource.AuthDataSourceImpl
import org.koin.dsl.module
import repository.AuthRepository
import repository.AuthRepositoryImpl

val dataModule = module {
    single<AuthDataSource> { AuthDataSourceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}