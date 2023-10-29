package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.ApiService.ApiService
import com.example.movieapp.databinding.ActivityDetailMovieBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFunctions()
    }

    private fun initFunctions() {
        getRetrofit()
        val id = intent.getStringExtra("EXTRA_ID").orEmpty()
        getMovieDetail(id)
    }

    private fun getRetrofit(): Retrofit {
        retrofit = Retrofit.Builder()
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
        return retrofit
    }

    private fun getMovieDetail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = getRetrofit()
            val myResponse = retrofit.create(ApiService::class.java).getMovieDetail(id)
            val title = myResponse.title
            val description = myResponse.description
            val backDropPath = myResponse.backdropPath
            val rating = myResponse.voteAverage.toDouble()
            val ratingStars = rating*0.5

            runOnUiThread {
                binding.tvDetailMovie.text = title
                binding.ratingBar.rating = ratingStars.toFloat()
                binding.tvDetailDescriptionMovie.text = description
                val baseUrl = "https://image.tmdb.org/t/p/w500"
                val backdrop = backDropPath
                val imgSrc = baseUrl + backdrop
                Picasso.get().load(imgSrc)
                    .into(binding.ivDetailMovie)
            }
        }
    }
}