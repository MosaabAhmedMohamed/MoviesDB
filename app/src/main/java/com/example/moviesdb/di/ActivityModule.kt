package com.example.moviesdb.di

import com.example.moviesdb.di.home.HomeFragmentsModule
import com.example.moviesdb.di.home.HomeModule
import com.example.moviesdb.di.home.HomeScope
import com.example.moviesdb.di.home.HomeViewModelModule
import com.example.moviesdb.ui.splash.SplashActivity
import com.example.moviesdb.di.splash.SplashScope
import com.example.moviesdb.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @SplashScope
    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity

    @HomeScope
    @ContributesAndroidInjector( modules = [HomeModule::class,
        HomeViewModelModule::class,
        HomeFragmentsModule::class])
    internal abstract fun homeActivity(): HomeActivity

}