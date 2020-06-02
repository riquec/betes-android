package com.jardim.betes.di

import com.jardim.betes.data.UserRepositoryImpl
import com.jardim.betes.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModules = module {
    single<UserRepository> { UserRepositoryImpl(get(), get(), Dispatchers.IO) }
}