package com.example.moviesdb.domin.usecase

import com.example.moviesdb.data.home.remote.model.MovieDetailModel
import com.example.moviesdb.domin.repository.MoviesRepository
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Inject

class MovieUseCase  @Inject constructor(private val repository: MoviesRepository) {

    fun getMovie(id: Int): Single<MovieDetailModel> {
        return repository.getMovie(id)
    }

    fun downloadFile(url: String): Single<ResponseBody> {
        return repository.downloadFile(url)
    }

}