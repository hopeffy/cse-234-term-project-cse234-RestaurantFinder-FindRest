package com.example.restaurantfinder

import android.content.ClipDescription
import android.content.Context
import com.example.restaurantfinder.data.Comment
import com.example.restaurantfinder.data.RestaurantType
import com.google.gson.Gson

class RestaurantData {

    data class RestaurantList(
        val restaurants : List<Restaurant>
    )
    data class Restaurant(
        val id: String = "",
        var name: String = "",
        val location : String = "",
        val Lat : Double = 0.0,
        val Lng : Double = 0.0,
        val restaurantTypeId : String = "",
        val totalScore: Double = 0.0,
        val totalNumberOfStars: Int = 1,
        val description: String = "",
        val comments: List<Comment> = emptyList()
    )

    data class RestaurantTypeList(
        val restaurantTypes : List<RestaurantType>
    )
    data class RestaurantType (
        val id : String = "",
        val name : String = ""
    )

    fun readRestaurants(context: Context, filename:String): List<Restaurant> {
        val jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        val resList = Gson().fromJson(jsonString, RestaurantList::class.java)
        return resList.restaurants
    }

    fun readRestaurantType(context: Context): List<RestaurantType> {
        val jsonRestType = context.assets.open("restaurantTypes.json").bufferedReader().use { it.readText() }
        val restTypeList  = Gson().fromJson(jsonRestType, RestaurantTypeList::class.java)
        return restTypeList.restaurantTypes
    }

    fun readRestaurantsJson(context: Context): List<Restaurant> {
        val jsonRestType = context.assets.open("restaurant.json").bufferedReader().use { it.readText() }
        val restTypeList  = Gson().fromJson(jsonRestType, RestaurantList::class.java)
        return restTypeList.restaurants
    }


    fun findRestType(context : Context) {
        val types = readRestaurantType(context)
        val restaurants = readRestaurantsJson(context)

        // Create a map from restaurantTypeId to restaurantTypeName
        val typeMap = types.associateBy({ it.id }, { it.name })

        // Update each restaurant with its type name
        val updatedRestaurants = restaurants.map { restaurant ->
            val typeName = typeMap[restaurant.restaurantTypeId] ?: "Unknown"
            restaurant.copy(name = "${restaurant.name} ($typeName)")
        }

        // Print the updated restaurant names
        updatedRestaurants.forEach { println(it.name) }
    }





}