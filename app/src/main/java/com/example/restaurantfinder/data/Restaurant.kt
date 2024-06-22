package com.example.restaurantfinder.data

/*
* A class to define restaurant
* */
data class Restaurant(
    val id: Long,
    val name: String,
    val location : String,
    val restaurantType: RestaurantType = RestaurantType.Default,
    val totalScore: Double,
    val totalNumberOfStars: Int = 1,
    val comments: List<Comment>
    )
