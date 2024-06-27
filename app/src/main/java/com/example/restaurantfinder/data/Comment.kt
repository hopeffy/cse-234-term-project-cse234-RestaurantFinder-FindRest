package com.example.restaurantfinder.data

import androidx.annotation.StringRes

/*
* A class to define comments
* */
data class Comment(
    val sender: Account,
    val restaurantId: String = "",
    val subject: String = "",
    val body: String = "",
    val score: Int = 1
)
