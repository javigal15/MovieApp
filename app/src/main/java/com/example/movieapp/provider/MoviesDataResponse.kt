package com.example.movieapp.provider

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class MoviesDataResponse(
    @SerializedName("results") val results: List<MovieItemResponse>
)

data class MovieItemResponse(
    @SerializedName("id") val id: String,
    @SerializedName("original_title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("popularity") val popularity: String,
    @SerializedName("release_Date") val date: String
)



