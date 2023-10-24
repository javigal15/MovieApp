package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    companion object {
        const val MOVIE_NAME = "movieName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(MOVIE_NAME).orEmpty()
        // getMovieDetail(name)
    }

    // private fun getMovieDetail(name: String) {
    //     binding.tvDetailMovie.text = getString(name.toInt())
    // }
}