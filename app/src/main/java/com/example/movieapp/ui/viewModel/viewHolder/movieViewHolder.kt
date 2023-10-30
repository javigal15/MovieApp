package com.example.movieapp.ui.viewModel.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.model.MovieItemResponse
import com.example.movieapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class movieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovieBinding.bind(view)

    fun render(movieInfo: MovieItemResponse, onItemSelected: (String) -> Unit) {

        val imgSrc = "https://image.tmdb.org/t/p/w500${movieInfo.posterPath}"
        Picasso.get().load(imgSrc)
            .into(binding.ivMovie)
        binding.tvMovie.text = movieInfo.title
        binding.ivMovie.setOnClickListener {
            onItemSelected(movieInfo.id)
        }
    }
}