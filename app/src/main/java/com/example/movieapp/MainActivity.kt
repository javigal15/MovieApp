package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.ApiService.ApiService
import com.example.movieapp.adapter.CarteleraAdapter
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var adapterCartelera: CarteleraAdapter


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
        orderMovies("")
        adapter = MovieAdapter { navigateToDetail(it) }
        adapterCartelera = CarteleraAdapter { navigateToDetail(it) }
        binding.rvMovies.adapter = adapter
        binding.rvCatalog.adapter = adapterCartelera
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvCatalog.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initListeners() {
        binding.btnPopularity.setOnClickListener { orderMovies("popularity") }
        binding.btnOrder.setOnClickListener { orderMovies("title") }
        binding.btnNewest.setOnClickListener { orderMovies("date") }
        binding.sMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMovie(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("EXTRA_ID", id)
        startActivity(intent)
    }

    private fun orderMovies(orderBy: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                RetrofitHelper.getRetrofit().create(ApiService::class.java).getMoviesName().body()
            if (response != null) {
                runOnUiThread {
                    adapterCartelera.updateList(response.results, "popularity")
                    adapter.updateList(response.results, orderBy)
                    binding.progressBar.isVisible = false
                }
            } else {
                Log.i("Javi", "Error al ordenar")
            }
        }
    }

    private fun searchMovie(query: String, year: String? = null) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitHelper.getRetrofit().create(ApiService::class.java)
                .getMoviesSpecificName(query, year).body()
            if (response != null) {
                runOnUiThread {
                    adapter.updateList(response.results)
                    binding.progressBar.isVisible = false
                }
            } else {
                Log.i("javi", "no anda el buscador")
            }

        }
    }

}