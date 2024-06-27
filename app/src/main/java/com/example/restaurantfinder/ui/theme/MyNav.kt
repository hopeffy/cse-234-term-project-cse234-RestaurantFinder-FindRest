package com.example.restaurantfinder.ui.theme

import Comments
import EditProfile
import Entrance
import FavoritesPage
import Profile_Layout
import SelectedRestaurant
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restaurantfinder.CommentData
import com.example.restaurantfinder.GoogleMapView
import com.example.restaurantfinder.HomePage

import com.example.restaurantfinder.ResetPassword
import com.example.restaurantfinder.RestaurantData
import com.example.restaurantfinder.SignInScreen
import com.example.restaurantfinder.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MyNav() {
    val navController = rememberNavController()
    lateinit var restList2 : List<RestaurantData.Restaurant>
    lateinit var comList : List<CommentData.Comment>
    lateinit var restaurantId : String
    lateinit var auth : FirebaseAuth

    NavHost(navController, startDestination = "entrance") {
        composable("sign_up") { SignUpScreen(navController) }
        composable("sign_in") { SignInScreen(navController) }
        composable("reset") { ResetPassword(navController) }
        composable("home") { HomePage(navController,restList2) }
        composable("edit") { EditProfile(navController) }
        composable("profile") { Profile_Layout(navController) }
        composable("comments") { Comments(navController, auth, comList, restList2) }
        composable("favorite") { FavoritesPage(navController) }
        composable("entrance") { Entrance(navController) }
        composable("map") { GoogleMapView(navController, restList2 = restList2) }

        composable(
            route = "selected/{restaurantId}",
            arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId")
            SelectedRestaurant(navController, commentList = comList, restaurantId = restaurantId , restList2 = restList2)
        }


    }
}
