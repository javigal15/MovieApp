package com.example.movieapp.ui.viewModel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.model.MovieItemResponse
import com.example.movieapp.ui.viewModel.viewHolder.carteleraViewHolder


class CarteleraAdapter(
    var movieList: List<MovieItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<carteleraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): carteleraViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cartelera, parent, false)
        return carteleraViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: carteleraViewHolder, position: Int) {
        holder.render(movieList[position], onItemSelected)
    }

    fun updateList(movieList: List<MovieItemResponse>, orderBy: String? = null) {

        this.movieList = when (orderBy) {
            "title" -> movieList.sortedBy { it.title }
            "popularity" -> movieList.sortedByDescending { it.popularity.toDouble() }
            "date" -> movieList.sortedBy { it.date }
            else -> movieList
        }
        notifyDataSetChanged()
    }
}