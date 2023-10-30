package com.example.movieapp.ui.viewModel.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemCarteleraBinding
import com.example.movieapp.data.model.MovieItemResponse
import com.squareup.picasso.Picasso

class carteleraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCarteleraBinding.bind(view)

    fun render(movieInfo: MovieItemResponse, onItemSelected: (String) -> Unit) {

        val imgSrc = "https://image.tmdb.org/t/p/w500${movieInfo.posterPath}"
        Picasso.get().load(imgSrc)
            .into(binding.ivMovieCart)
        binding.tvMovieCart.text = movieInfo.title
        binding.ivMovieCart.setOnClickListener {
            onItemSelected(movieInfo.id)
        }
    }
}