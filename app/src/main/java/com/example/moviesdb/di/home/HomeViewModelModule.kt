package com.example.moviesdb.di.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesdb.presentation.home.ViewModelFactory
import com.example.moviesdb.presentation.home.viewmodel.MovieViewModel
import com.example.moviesdb.presentation.home.viewmodel.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @HomeViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(homeViewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @HomeViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(homeViewModel: MovieViewModel): ViewModel

}