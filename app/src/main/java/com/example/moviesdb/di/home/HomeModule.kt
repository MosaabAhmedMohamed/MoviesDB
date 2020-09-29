package com.example.moviesdb.di.home

import com.example.moviesdb.data.home.HomeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object HomeModule {

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideHomeApi(retrofit: Retrofit.Builder): HomeApi {
        return retrofit
            .build()
            .create(HomeApi::class.java)
    }


}