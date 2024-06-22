package com.example.restaurantfinder.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/*
* A class to define account
* */
data class Account(
    val id: Long,
    @StringRes val firstName: Int,
    @StringRes val lastName: Int,
    @StringRes val email: Int,
    @StringRes val password: Int,
    @StringRes val phone: Int,
    /** User's avatar image resource id **/
    @DrawableRes val avatar: Int
)
