package com.example.moviesdb.data.home.repository.datasource

import androidx.annotation.NonNull
import androidx.paging.rxjava2.RxPagingSource
import com.example.moviesdb.data.home.remote.client.HomeApi
import com.example.moviesdb.data.home.remote.model.MovieModel
import com.example.moviesdb.data.home.remote.model.Results
import com.example.moviesdb.util.Constants.Companion.API_KEY

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MoviesDataSource(
    private val api: HomeApi
) : RxPagingSource<Int, Results>() {


    private fun toLoadResult(@NonNull response: MovieModel): LoadResult<Int, Results> {
        return LoadResult.Page(
            response.results,
            null,  // Only paging forward.
            response.page + 1,
            LoadResult.Page.COUNT_UNDEFINED,
            LoadResult.Page.COUNT_UNDEFINED
        )
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Results>> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1

        return api.getMovies(API_KEY, nextPageNumber)
            .subscribeOn(Schedulers.io())
            .map { response -> toLoadResult(response) }
            .onErrorReturn { toLoadError(it) }

    }

    private fun toLoadError(@NonNull e: Throwable): LoadResult<Int, Results> {
        return LoadResult.Error(e)
    }

}
