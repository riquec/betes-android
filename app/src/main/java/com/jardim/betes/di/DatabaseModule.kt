package com.jardim.betes.di

import androidx.room.Room
import com.jardim.betes.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(),AppDatabase::class.java,"BetesDatabase")
            .build()
    }

    single { get<AppDatabase>().userDao() }
}