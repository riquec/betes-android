package com.jardim.betes.di

import com.jardim.betes.di.providers.CreateUserViewModelFactory
import com.jardim.betes.di.providers.LoginViewModelFactory
import com.jardim.betes.di.providers.SplashViewModelFactory
import com.jardim.betes.ui.login.LoginViewModel
import com.jardim.betes.ui.login.create.CreateUserViewModel
import com.jardim.betes.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModelFactory().create(SplashViewModel::class.java) }
    viewModel { CreateUserViewModelFactory(get()).create(CreateUserViewModel::class.java) }
    viewModel { LoginViewModelFactory(get()).create(LoginViewModel::class.java) }
}