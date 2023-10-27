package com.example.movieapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.ApiService.MovieItemResponse
import com.example.movieapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class movieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovieBinding.bind(view)

    fun render(movieInfo: MovieItemResponse) {
        //Picasso.get().load(movieInfo.imageFront)
         //   .into(binding.ivMovie)
        binding.tvMovie.text = movieInfo.title
    }
}