package com.example.moviesdb.di.home

import com.example.moviesdb.ui.home.fragment.MovieDetailFragment
import com.example.moviesdb.ui.home.fragment.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment


}