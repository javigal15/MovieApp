package com.example.movieapp.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("accept", "application/json")
                    .addHeader(
                        "Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZjRlZjZhYWVhYWI4ZjE2NDkwZDM3NzgzYzYwY2M0YSIsInN1YiI6IjY1Mzg1MDc0OWMyNGZjMDEyNjU2NzI1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NyBfq3p6GMi2tE4HQc_jSq_UtyMIe683PPu771dcjMU"
                    )
                    .build()
                chain.proceed(request)
            }.build()).build()
    }
}