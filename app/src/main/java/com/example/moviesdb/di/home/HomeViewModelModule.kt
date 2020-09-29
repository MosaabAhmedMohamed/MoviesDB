package com.example.moviesdb.di.home

import androidx.lifecycle.ViewModelProvider
import com.example.moviesdb.presentation.home.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class HomeViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}