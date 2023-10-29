package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.ApiService.ApiService
import com.example.movieapp.provider.MoviesDataResponse
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var retrofit: Retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initData()
        initResponse()
        initRecyclerView()
        initListeners()
    }

    private fun initData() {
        getRetrofit()
    }

    private fun initResponse() {
        CoroutineScope(Dispatchers.IO).launch { getResponse() }
        orderMovies("")
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter { navigateToDetail(it) }
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter
        binding.rvCatalog.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCatalog.adapter = adapter
    }

    private fun initListeners() {
        binding.btnPopularity.setOnClickListener { orderMovies("popularity") }
        binding.btnOrder.setOnClickListener { orderMovies("title") }
        binding.btnNewest.setOnClickListener { orderMovies("date") }
        binding.sMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
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

    private suspend fun getResponse(): MoviesDataResponse? {
        val myResponse = retrofit.create(ApiService::class.java).getMoviesName()
        val response = myResponse.body()
        return response
    }

    private fun orderMovies(orderBy: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = getResponse()
            if (response != null) {
                runOnUiThread {
                    adapter.updateList(response.results, orderBy)
                    binding.progressBar.isVisible = false
                    //binding.rvMovies.adapter = adapter
                }
            } else {
                Log.i("Javi", "Error al ordenar")
            }
        }
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        retrofit = getRetrofit()
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getMoviesSpecificName(query)
            if (myResponse.isSuccessful) {
                val response: MoviesDataResponse? = myResponse.body()
                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.results)
                        binding.progressBar.isVisible = false
                    }
                } else {
                    Log.i("javi", "no anda")
                }
            }
        }
    }


}