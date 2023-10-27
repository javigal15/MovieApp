package com.example.movieapp.provider

import com.example.movieapp.R

public open class movieList(val img: Int, val name: Int, val desc:Int) {
    data object Javi : movieList(R.drawable.libra, R.string.Javi,R.string.Desc1)
    data object Dani : movieList(R.drawable.virgo, R.string.Dani,R.string.Desc2)
}