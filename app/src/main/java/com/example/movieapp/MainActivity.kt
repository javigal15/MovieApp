package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.MovieAdapter

import com.example.movieapp.provider.movieProvider
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.provider.movieList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter =
            MovieAdapter(movieProvider.myMovieList) { onItemSelected(movieList.Javi) }
    }

    private fun onItemSelected(movieList: movieList.Javi) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("MOVIE_NAME", movieList.name)
        startActivity(intent)
    }


}