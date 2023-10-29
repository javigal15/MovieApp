package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.provider.MovieItemResponse
import com.example.movieapp.R
import com.example.movieapp.viewHolder.movieViewHolder
import java.text.SimpleDateFormat
import java.util.Locale


class MovieAdapter(
    var movieList: List<MovieItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
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