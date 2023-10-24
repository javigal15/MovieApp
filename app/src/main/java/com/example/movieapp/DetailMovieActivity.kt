package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    companion object {
        const val EXTRA_ID = "extra_id"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getMovieDetail(id)
    }

    private fun getMovieDetail(id: String) {

      //  binding.ivDetailMovie =
      //  binding.tvDetailMovie =
       // binding.tvDetailDescriptionMovie =

    }
}