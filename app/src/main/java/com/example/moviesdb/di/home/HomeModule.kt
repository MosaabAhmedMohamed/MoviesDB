package com.example.moviesdb.di.home

import com.example.moviesdb.data.home.remote.client.HomeApi
import com.example.moviesdb.data.home.repository.MovieRepositoryImpl
import com.example.moviesdb.domin.repository.MoviesRepository
import com.example.moviesdb.domin.usecase.MovieUseCase
import com.example.moviesdb.domin.usecase.MoviesUseCase
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

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideMovieRepository(api: HomeApi): MoviesRepository {
        return MovieRepositoryImpl(api)
    }

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideMovieUseCase(repository: MoviesRepository): MovieUseCase {
        return MovieUseCase(repository)
    }

    @JvmStatic
    @HomeScope
    @Provides
    internal fun provideMoviesUseCase(repository: MoviesRepository): MoviesUseCase {
        return MoviesUseCase(repository)
    }

}