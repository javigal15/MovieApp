package com.example.movieapp.ApiService

import com.google.gson.annotations.SerializedName

data class movieDataResponse(
    @SerializedName("movies") val movies: List<MoviesItemResponse>
)

data class MoviesItemResponse(
    @SerializedName("_id") val movieId: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("backdrop_path") val imageBack: String,
    @SerializedName("genres") val genres: MovieGenresResponse,
    @SerializedName("overview") val description: String,
    @SerializedName("poster_path") val imageFront: String
)

data class MovieGenresResponse(
    @SerializedName("0") val Action: String,
    @SerializedName("1") val Comedy: String,
    @SerializedName("2") val Romance: String,
)