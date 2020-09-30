package com.example.moviesdb.data.home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.moviesdb.data.home.remote.client.HomeApi
import com.example.moviesdb.data.home.remote.model.MovieDetailModel
import com.example.moviesdb.data.home.remote.model.Results
import com.example.moviesdb.data.home.repository.datasource.MoviesDataSource
import com.example.moviesdb.domin.repository.MoviesRepository
import com.example.moviesdb.util.Constants.Companion.API_KEY
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Inject

class MovieRepositoryImpl
@Inject
constructor(
    private val api: HomeApi
) : MoviesRepository {


    override fun getMovie(id: Int): Single<MovieDetailModel> {
        return api.getMovieDetail(id, API_KEY)
    }

    override fun getMoviesPager(): Pager<Int, Results> {
        return Pager(
            PagingConfig( /* pageSize = */15, enablePlaceholders =  false),
            pagingSourceFactory = { MoviesDataSource(api) })
    }

    override fun downloadFile(url: String): Single<ResponseBody> {
        return api.downloadFile(url)
    }
}