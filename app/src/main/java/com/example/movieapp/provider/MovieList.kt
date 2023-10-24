package com.example.movieapp.provider

import com.example.movieapp.R

open class movieList(val img: Int, val name: Int) {
    data object Javi : movieList(R.drawable.libra, R.string.Javi)
    data object Dani : movieList(R.drawable.virgo, R.string.Dani)
}