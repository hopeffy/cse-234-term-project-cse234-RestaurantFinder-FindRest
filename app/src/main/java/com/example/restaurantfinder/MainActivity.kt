package com.example.restaurantfinder


import Comments
import EditProfile
import Entrance
import FavoritesPage
import Profile_Layout
import SelectedRestaurant
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.restaurantfinder.ui.theme.MyNav

import com.example.restaurantfinder.ui.theme.RestaurantFinderTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

data class BottomNavigationItem(

    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null


)

class MainActivity : ComponentActivity() {

    lateinit var auth : FirebaseAuth

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContent {
            RestaurantFinderTheme {

                val navController = rememberNavController()

                val items = listOf(

                    BottomNavigationItem(

                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false,
                    ),
                    BottomNavigationItem(

                        title = "Map",
                        selectedIcon = Icons.Filled.Place,
                        unselectedIcon = Icons.Outlined.Place,
                        hasNews = false,
                    ),
                    BottomNavigationItem(

                        title = "Favorite",
                        selectedIcon = Icons.Filled.Favorite,
                        unselectedIcon = Icons.Outlined.Favorite,
                        hasNews = false,
                    ),
                    BottomNavigationItem(

                        title = "Profile",
                        selectedIcon = Icons.Filled.Person,
                        unselectedIcon = Icons.Outlined.Person,
                        hasNews = true,
                    )
                )

                var selectedItemIndex by rememberSaveable {

                    mutableStateOf(0)

                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {

                    Scaffold (

                        bottomBar = {

                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            if (currentRoute !in listOf("entrance", "sign_in", "sign_up")) {
                            NavigationBar {

                                items.forEachIndexed { index, item ->

                                    NavigationBarItem(

                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                           navController.navigate(item.title)

                                        },
                                        label = {

                                              Text(text = item.title)

                                        },
                                        alwaysShowLabel = false,
                                        icon = {

                                            BadgedBox(
                                                badge = {

                                                    if (item.badgeCount != null) {

                                                        Badge {
                                                            Text(text = item.badgeCount.toString() )

                                                        }
                                                    }
                                                    else if (item.hasNews){
                                                        Badge()

                                                    }
                                                }
                                            ) {

                                                Icon(
                                                    imageVector = if (index == selectedItemIndex){
                                                        item.selectedIcon }
                                                    else item.unselectedIcon
                                                    ,
                                                    contentDescription = item.title
                                                )
                                            }
                                        }

                                    )


                                }

                            }
                            }
                        }
                    )

                    {
                        innerPadding ->

                        NavHost(navController, startDestination = "entrance") {
                            composable("sign_up") { SignUpScreen(navController) }
                            composable("sign_in") { SignInScreen(navController) }
                            composable("reset") { ResetPassword(navController) }
                            composable("home") { HomePage(navController,) }
                            composable("edit") { EditProfile(navController) }
                            composable("profile") { Profile_Layout(navController) }
                            composable("comments") { Comments(navController) }
                            composable("selected") { SelectedRestaurant(navController) }
                            composable("favorite") { FavoritesPage(navController) }
                            composable("entrance") { Entrance(navController) }

                        }
                    }
                }
            }
        }



    }

    override fun onStart() {
        super.onStart()
        checkLoggedInState()
    }

    private fun registerUser(email: String, password: String) {

        if(email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main) {
                        checkLoggedInState()
                    }
                }catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun checkLoggedInState() {
        if(auth.currentUser == null) {
            Toast.makeText(this, "You are not logged in", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You are logged in", Toast.LENGTH_LONG).show()
        }
    }

    private fun loginUser(email: String, password: String) {

        if(email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main) {
                        checkLoggedInState()
                    }
                }catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}


