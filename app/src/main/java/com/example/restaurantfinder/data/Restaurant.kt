package com.example.restaurantfinder.data

/*
* A class to define restaurant
* */
data class Restaurant(
    val id: String = "",
    val name: String = "",
    val location : String = "",
    val restaurantType: RestaurantType = RestaurantType(name),
    val totalScore: Double = 0.0,
    val totalNumberOfStars: Int = 1,
    val comments: List<Comment> = emptyList()
    )
