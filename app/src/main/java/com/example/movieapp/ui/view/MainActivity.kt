package com.example.movieapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.core.NetworkModule.provideRetrofit
import com.example.movieapp.data.model.network.ApiService
import com.example.movieapp.ui.viewModel.adapter.CarteleraAdapter
import com.example.movieapp.ui.viewModel.adapter.MovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        orderMovies("", null)
        adapter = MovieAdapter { navigateToDetail(it) }
        adapterCartelera = CarteleraAdapter { navigateToDetail(it) }
        binding.rvMovies.adapter = adapter
        binding.rvCatalog.adapter = adapterCartelera
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvCatalog.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initListeners() {
        binding.btnPopularity.setOnClickListener { orderMovies("popularity", null) }
        binding.btnOrder.setOnClickListener { orderMovies("title", null) }
        binding.btnNewest.setOnClickListener { orderMovies("date", null) }
        binding.sMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                orderMovies("", query.orEmpty())
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

    private fun orderMovies(orderBy: String, query: String?) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                provideRetrofit().create(ApiService::class.java).getMoviesName().body()
            if (query != null) {
                val searchResponse = provideRetrofit().create(ApiService::class.java)
                    .getMoviesSpecificName(query).body()
                if (searchResponse != null) {
                    runOnUiThread {
                        adapter.updateList(searchResponse.results)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
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
    }
}