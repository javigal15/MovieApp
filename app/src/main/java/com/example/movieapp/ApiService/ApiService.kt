package com.example.movieapp.ApiService

import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    //@GET("movie")
    //suspend fun getMoviesName(): retrofit2.Response<MoviesDataResponse>

    @GET("search/{name}")
    suspend fun getMoviesSpecificName(@Path("name") movieSpecificName: String): retrofit2.Response<MoviesDataResponse>

    @GET("discover/movie")
    suspend fun getMoviesName(): retrofit2.Response<MoviesDataResponse>

    //@GET("discover/movie")
    //suspend fun getMoviesName(): retrofit2.Response<MoviesDataResponse>
}
