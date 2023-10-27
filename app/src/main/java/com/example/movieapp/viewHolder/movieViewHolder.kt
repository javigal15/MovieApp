package com.example.movieapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.ApiService.MovieItemResponse
import com.example.movieapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class movieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovieBinding.bind(view)

    fun render(movieInfo: MovieItemResponse) {

        val baseUrl = "https://image.tmdb.org/t/p/w500"
        val posterPath = movieInfo.poster_path
        val imgSrc = baseUrl + posterPath

        Picasso.get().load(imgSrc)
            .into(binding.ivMovie)
        binding.tvMovie.text = movieInfo.title
    }
}