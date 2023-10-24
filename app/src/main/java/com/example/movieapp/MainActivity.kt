package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.movieAdapter
import com.example.movieapp.provider.movieProvider
import com.example.movieapp.databinding.ActivityMainBinding


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
        initListeners()
    }

    private fun initRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = movieAdapter(movieProvider.myMovieList)

    }

    private fun initListeners() {

    }
}