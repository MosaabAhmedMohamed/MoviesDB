package com.example.moviesdb.di

import com.example.moviesdb.ui.SplashActivity
import com.example.moviesdb.di.splash.SplashScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @SplashScope
    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity

}