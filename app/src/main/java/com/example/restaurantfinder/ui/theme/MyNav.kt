package com.example.restaurantfinder.ui.theme

import Comments
import EditProfile
import Profile_Layout
import SelectedRestaurant
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantfinder.HomePage
import com.example.restaurantfinder.ResetPassword
import com.example.restaurantfinder.SignInScreen
import com.example.restaurantfinder.SignUpScreen

@Composable
fun MyNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "sign_up") {
        composable("sign_up") { SignUpScreen(navController) }
        composable("sign_in") { SignInScreen(navController) }
        composable("reset") { ResetPassword(navController) }
        composable("home") { HomePage(navController) }
        composable("edit") { EditProfile(navController) }
        composable("profile") { Profile_Layout(navController) }
        composable("comments") { Comments(navController) }
        composable("selected") { SelectedRestaurant(navController) }



    }
}
