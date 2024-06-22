package com.example.restaurantfinder.data

import androidx.annotation.StringRes

/*
* A class to define comments
* */
data class Comment(
    val id: Long,
    val sender: Account,
    val restaurantId: Restaurant,
    @StringRes val subject: Int = -1,
    @StringRes val body: Int = -1,
    val score: Int = 1,
)
