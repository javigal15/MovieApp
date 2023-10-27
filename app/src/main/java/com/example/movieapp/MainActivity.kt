package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.ApiService.ApiService
import com.example.movieapp.ApiService.MoviesDataResponse
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
        adapter = MovieAdapter(emptyList())
        getMovies()
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.adapter = adapter
    }

    private fun getMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = getRetrofit()
            val myResponse = retrofit.create(ApiService::class.java).getMoviesName()
            if (myResponse.isSuccessful) {
                val response: MoviesDataResponse? = myResponse.body()
                if (response != null) {
                    runOnUiThread {
                        adapter.updateList(response.results)
                        binding.rvMovies.adapter = adapter
                    }
                }
            } else {
                Log.i("Javi", "Hay algun error")
            }
        }
    }

    private fun getRetrofit(): Retrofit {

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
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
}