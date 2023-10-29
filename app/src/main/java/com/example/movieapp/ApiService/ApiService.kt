package com.example.movieapp.ApiService

import com.example.movieapp.provider.MovieDetailResponse
import com.example.movieapp.provider.MovieItemResponse
import com.example.movieapp.provider.MoviesDataResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    //@GET("search/{name}")
    //suspend fun getMoviesSpecificName(@Path("name") movieSpecificName: String): retrofit2.Response<MoviesDataResponse>

    @GET("search/movie?{query}")
    suspend fun getMoviesSpecificName(@Path("query") movieSpecificName: String): retrofit2.Response<MoviesDataResponse>

    @GET("discover/movie")
    suspend fun getMoviesName(): retrofit2.Response<MoviesDataResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: String): MovieDetailResponse

}
