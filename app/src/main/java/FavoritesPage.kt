import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.restaurantfinder.R

@Composable
fun FavoritesPage(navController: NavHostController) {
    var favRest by remember { mutableStateOf("") }

    val favoriteRestaurants = listOf("StarBucks", "Milk Bar","Tavuk Dünyası", "Köfteci İlhan") // Dummy data

    val filteredRestaurants = favoriteRestaurants.filter { restaurant ->
        restaurant.contains(favRest, ignoreCase = true)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "MY FAVORITES",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.25.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            OutlinedTextField(
                value = favRest,
                onValueChange = { favRest = it },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50.dp),
                maxLines = 1,
                placeholder = { Text(text = "Search favorites...") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredRestaurants) { restaurant ->
                    FavoriteRestaurantCard(restaurant, navController)
                }
            }
        }
    }
}

@Composable
fun FavoriteRestaurantCard(restaurantName: String, navController: NavHostController) {
    var isFavorite by remember { mutableStateOf(true) }

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
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
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
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(restaurantName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Location", Modifier.clickable { navController.navigate("map") })

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
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

            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.Favorite,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.Gray,
                modifier = Modifier
                    .clickable { isFavorite = !isFavorite }
                    .padding(8.dp)
            )
        }
    }
}

