package com.example.movieapp.provider

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("original_title") val title: String,
    @SerializedName("overview") val description: String,
    @SerializedName("backdrop_path") val backdropPath: String
)