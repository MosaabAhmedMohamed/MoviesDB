package com.example.moviesdb.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moviesdb.data.home.remote.model.Results
import com.example.moviesdb.domin.usecase.MoviesUseCase
import com.example.moviesdb.util.rx.AppSchedulerProvider
import javax.inject.Inject

class MoviesViewModel
@Inject
constructor(
    private val useCase: MoviesUseCase
) : BaseViewModel(
    AppSchedulerProvider.io(),
    AppSchedulerProvider.ui()
) {

    private var movies: Pager<Int, Results>? = null


    fun moviesList(): LiveData<PagingData<Results>> {
        if (movies == null) movies = useCase.getMoviesPager()

        return movies!!.liveData
    }

    override fun onCleared() {
        super.onCleared()
        movies = null
    }

}

