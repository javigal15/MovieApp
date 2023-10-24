package com.example.movieapp.provider

import com.google.gson.annotations.SerializedName

data class MovieDetailProvider(
    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("descripcion")
    val descripcion: String,
)