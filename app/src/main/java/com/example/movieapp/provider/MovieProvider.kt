package com.example.movieapp.provider

import com.example.movieapp.R

class movieProvider {
    companion object {
        val myMovieList = listOf<movieList>(
            movieList(R.drawable.libra, R.string.Javi),
            movieList(R.drawable.virgo, R.string.Dani),
        )
    }
}