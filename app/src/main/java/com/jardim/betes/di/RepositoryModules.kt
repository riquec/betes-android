package com.jardim.betes.di

import com.jardim.betes.data.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModules = module {
    single { UserRepositoryImpl() }
}