package com.example.moviesdb.domin.repository

import androidx.paging.Pager
import com.example.moviesdb.data.home.remote.model.MovieDetailModel
import com.example.moviesdb.data.home.remote.model.Results
import io.reactivex.Single

interface MoviesRepository {

    fun getMovie(id: Int): Single<MovieDetailModel>

    fun getMoviesPager(): Pager<Int, Results>
}