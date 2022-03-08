package com.sample.neuroidapp.us.extensions

fun getDays(position: Int): List<Int> {
    return when(position) {
        0,2,4,6,7,9,11 -> (1..31).toList()
        3,5,8,10 -> (1..30).toList()
        else -> (1..28).toList()
    }
}