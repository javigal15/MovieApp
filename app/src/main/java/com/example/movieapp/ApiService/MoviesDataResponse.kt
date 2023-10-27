package com.example.movieapp.ApiService

import com.google.gson.annotations.SerializedName

data class MoviesDataResponse(
    @SerializedName("results") val results: List<MovieItemResponse>
)

data class MovieItemResponse(
    @SerializedName("id") val movieId: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") var poster_path: String,

    )

data class MovieDataResponse(
    @SerializedName("movie") val movie: MovieItemResponse
)

