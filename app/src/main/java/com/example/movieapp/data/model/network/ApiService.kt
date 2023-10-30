package com.example.movieapp.data.model.network

import com.example.movieapp.data.model.MovieDetailResponse
import com.example.movieapp.data.model.MoviesDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("search/movie")
    suspend fun getMoviesSpecificName(
        @Query("query") movieSpecificName: String,
        @Query("year") movieSpecificYear: String?
    ): retrofit2.Response<MoviesDataResponse>

    @GET("discover/movie")
    suspend fun getMoviesName(): retrofit2.Response<MoviesDataResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: String): MovieDetailResponse

}