package com.example.moviesdb.data.home.remote.client

import com.example.moviesdb.data.home.remote.model.MovieDetailModel
import com.example.moviesdb.data.home.remote.model.MovieModel
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface HomeApi{

    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") pageNumber: Int
    ): Single<MovieModel>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String?
    ): Single<MovieDetailModel>

    @GET
    fun downloadFile(
        @Url fileUrl: String
    ): Single<ResponseBody>

}