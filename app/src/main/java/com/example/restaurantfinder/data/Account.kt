package com.example.restaurantfinder.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/*
* A class to define account
* */
data class Account(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    /** User's avatar image resource id **/
    val avatarUrl: String = ""
)
