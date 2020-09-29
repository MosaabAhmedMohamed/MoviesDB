package com.example.moviesdb.domin.usecase

import androidx.paging.Pager
import com.example.moviesdb.data.home.remote.model.Results
import com.example.moviesdb.domin.repository.MoviesRepository
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val repository: MoviesRepository) {


    fun getMoviesPager(): Pager<Int, Results> {
        return repository.getMoviesPager()
    }
}