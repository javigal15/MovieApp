package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse (
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val description: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val voteAverage: String
)