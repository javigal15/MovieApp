package com.example.movieapp.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.provider.movieList
import com.example.movieapp.databinding.ItemMovieBinding

class movieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemMovieBinding.bind(view)

    fun render(movieInfo: movieList, onClick: (String) -> Unit) {
        val context = binding.tvMovie.context
        binding.ivMovie.setImageResource(movieInfo.img)
        binding.tvMovie.text = context.getString(movieInfo.name)
        binding.root.setOnClickListener { onClick(movieInfo.name.toString()) }
    }
}
