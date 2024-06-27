package com.example.restaurantfinder

import android.content.Context
import com.example.restaurantfinder.data.Account
import com.example.restaurantfinder.data.Comment
import com.example.restaurantfinder.data.Restaurant
import com.example.restaurantfinder.data.RestaurantType
import com.google.gson.Gson

class CommentData {
    data class CommentList(
        val comments : List<Comment>
    )
    data class Comment(
        val id: Int = 0,
        val senderId: String = "",
        val restaurantId: String = "",
        val subject: String = "",
        val body: String = "",
        val score: Int = 1
    )

    fun readComments(context: Context, filename:String): List<Comment> {
        val jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        val commentList = Gson().fromJson(jsonString, CommentList::class.java)
        return commentList.comments
    }

}