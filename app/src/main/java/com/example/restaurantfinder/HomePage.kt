package com.example.restaurantfinder

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController, restList: List<RestaurantData.Restaurant>, modifier: Modifier = Modifier) {

    var searchRest by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }



          Column {

              OutlinedTextField(value = searchRest,
                  onValueChange = {  searchRest= it },


                  trailingIcon = {
                      Icon(
                          imageVector = Icons.Default.Search,
                          contentDescription = "Search Icon"
                      )
                  },
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(horizontal = 16.dp, vertical = 8.dp),
                  shape = RoundedCornerShape(50.dp),
                  maxLines = 1,

                  )

              Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "EXPLORE RESTAURANT",
                style = TextStyle(

                  fontSize = 24.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.25.sp
                )
                ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

              CategoryTabs(selectedTab, onTabSelected = { selectedTab = it })

              when (selectedTab) {
                  0 -> CoffeeList(navController, searchRest)
                  1 -> BakeryList(navController , searchRest)
                  2 -> ChineseList(navController , searchRest)
                  3 -> AllRest(navController, restList)
              }
        }
    }

@Composable
fun AllRest(navController: NavHostController, restList: List<RestaurantData.Restaurant>) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(restList) { restaurant ->
            card(restaurant, navController)
        }
    }
}

@Composable
fun CategoryTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Cafe", "Bakery", "Chinese", "All")

    TabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = { Text(title) }
            )
        }
    }
}



@Composable
fun CoffeeList(navController: NavHostController , filter: String = "") {

    var restaurantList = mutableListOf(
        "StarBucks", "Milk Bar", "Arabica", "Steam", "Mack Bear", "Hang Out"
    ).filter { it.contains(filter, ignoreCase = true) }.map { "Cafe: $it" }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(restaurantList) { restaurant ->
            RestaurantCard(restaurant , navController )
        }
    }
}
@Composable
fun BakeryList(navController: NavHostController, filter: String = "" ) {

    var bakeryList = mutableListOf(
        "Günes Fırını", "Simit Sarayı", "Deniz Simit Fırını", "Ata Fırın-Cafe", "Ekin Fırın Cafe"
    ).filter { it.contains(filter, ignoreCase = true) }.map { "Bakery: $it" }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bakeryList) { bakery ->
            BakeryCard(bakery , navController )
        }
    }
}
@Composable
fun ChineseList(navController: NavHostController, filter: String = "" ) {

    var chineseList = mutableListOf(
        "Tavuk Dünyası", "Köfteci İlhan", "Burger King", "Kırık Tezgah", "Meltem Restaurant", "Dünya Döner"
    ).filter { it.contains(filter, ignoreCase = true) }.map { "Chinese: $it" }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(chineseList) { chinese ->
            ChineseCard(chinese , navController )
        }
    }
}

@Composable
fun RestaurantCard(restaurantName: String ,navController: NavHostController ) {

    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {

                navController.navigate("selected") }
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.restaurant),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(restaurantName, fontWeight = FontWeight.Bold)
                    Text(
                        "Location",
                        Modifier.clickable { navController.navigate("map") }
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("4.5", color = Color.Gray, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.weight(1f))


                }
            }

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { isFavorite = !isFavorite }
                    .padding(end = 8.dp)
            )
        }
    }
}

@Composable
fun BakeryCard(bakeryName: String, navController: NavHostController) {
    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { navController.navigate("selected") }
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.restaurant),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(bakeryName, fontWeight = FontWeight.Bold)
                    Text(
                        "Location",
                        Modifier.clickable { navController.navigate("profile") }
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("4.5", color = Color.Gray, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.weight(1f))


                    }
                }

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { isFavorite = !isFavorite }
                    .padding(end = 8.dp)
            )

        }
    }
}


@Composable
fun ChineseCard(restaurantName: String, navController: NavHostController) {
    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { navController.navigate("selected") }
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.restaurant),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(restaurantName, fontWeight = FontWeight.Bold)
                    Text(
                        "Location",
                        Modifier.clickable { navController.navigate("profile") }
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("4.5", color = Color.Gray, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.weight(1f))


                }
            }

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { isFavorite = !isFavorite }
                    .padding(end = 8.dp)
            )
        }
    }
}


@Composable
fun card(restaurant : RestaurantData.Restaurant ,navController: NavHostController) {

    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {

                navController.navigate("selected/${restaurant.id}") }
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.restaurant),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(restaurant.name, fontWeight = FontWeight.Bold)
                    Text(
                        "Location",
                        Modifier.clickable { navController.navigate("map") }
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("4.5", color = Color.Gray, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.weight(1f))


                }
            }

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { isFavorite = !isFavorite }
                    .padding(end = 8.dp)
            )
        }
    }
}
