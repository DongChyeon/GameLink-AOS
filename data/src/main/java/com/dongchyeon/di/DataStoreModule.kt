package com.dongchyeon.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DB_NAME = "gamelink_datastore.preferences_pb"

val dataStoreModule = module {
    single { providesDataStore(androidContext()) }
}

fun providesDataStore(context: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(produceFile = { context.dataStoreFile(
        DB_NAME
    ) })
}