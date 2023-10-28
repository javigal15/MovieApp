package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.ApiService.MovieItemResponse
import com.example.movieapp.R
import com.example.movieapp.viewHolder.movieViewHolder


class MovieAdapter(
    private var movieList: List<MovieItemResponse>
) :
    RecyclerView.Adapter<movieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return movieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: movieViewHolder, position: Int) {
        holder.render(movieList[position])
    }

    fun updateList(movieList: List<MovieItemResponse>, orderBy: String? = null) {
        this.movieList = when (orderBy) {
            "title" -> movieList.sortedBy { it.title }
            "popularity" -> movieList.sortedBy { it.popularity }
            "date" -> movieList.sortedBy { it.date }
            else -> movieList
        }
        notifyDataSetChanged()
    }
}