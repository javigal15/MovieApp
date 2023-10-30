package com.example.movieapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.core.NetworkModule
import com.example.movieapp.data.model.network.ApiService
import com.example.movieapp.databinding.ActivityDetailMovieBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFunctions()
    }

    private fun initFunctions() {
        val id = intent.getStringExtra("EXTRA_ID").orEmpty()
        getMovieDetail(id)
    }

    private fun getMovieDetail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse =
                NetworkModule.provideRetrofit().create(ApiService::class.java).getMovieDetail(id)
            runOnUiThread {
                binding.tvDetailMovie.text = myResponse.title
                binding.ratingBar.rating = (myResponse.voteAverage.toDouble() * 0.5).toFloat()
                binding.tvDetailDescriptionMovie.text = myResponse.description
                val baseUrl = "https://image.tmdb.org/t/p/w500${myResponse.backdropPath}"
                Picasso.get().load(baseUrl)
                    .into(binding.ivDetailMovie)
            }
        }
    }
}