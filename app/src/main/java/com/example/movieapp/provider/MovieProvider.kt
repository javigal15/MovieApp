package com.example.movieapp.provider

import com.example.movieapp.R

class movieProvider {
    companion object {
        val myMovieList = listOf<movieList>(
            movieList(R.drawable.libra, R.string.Javi,R.string.Desc1),
            movieList(R.drawable.virgo, R.string.Dani,R.string.Desc2),
        )
    }
}