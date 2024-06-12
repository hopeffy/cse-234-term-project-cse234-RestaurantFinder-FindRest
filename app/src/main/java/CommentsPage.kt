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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
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
import com.example.restaurantfinder.R
import com.example.restaurantfinder.RestaurantCard

@Composable
fun Comments(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "COMMENTS",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 1.25.sp
            )
        )
        RestaurantList(navController)
    }
}

@Composable
fun RestaurantList(navController: NavHostController) {
    val restaurantList = listOf("a", "b", "c", " d", "e").map { "Restaurant Name: $it" }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(restaurantList) { restaurant ->
            CommentCard(restaurantName = restaurant, navController = navController)
        }
    }
}

@Composable
fun CommentCard(restaurantName: String, navController: NavHostController) {
    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {  }
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
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

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text("Location", modifier = Modifier.clickable { navController.navigate("profile") })
                Text(restaurantName , fontWeight = FontWeight.Bold)
                Text("Comment:")

                Row(
                    verticalAlignment = Alignment.CenterVertically
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

            Column (horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Center ){
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Yellow else Color.Gray,
                    modifier = Modifier
                        .clickable { isFavorite = !isFavorite }
                        .padding(end = 8.dp)
                )
            }
        }
    }
}