import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.restaurantfinder.R

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.example.restaurantfinder.CommentData
import com.example.restaurantfinder.RestaurantData
import com.example.restaurantfinder.card

@Composable
fun SelectedRestaurant(navController: NavHostController, commentList : List<CommentData.Comment>, restaurantId: String?,
                       restList2 : List<RestaurantData.Restaurant>) {

    var filterCom = filterComment(commentList = commentList, restId = restaurantId)
    var findRest = findRestaurant(restList = restList2, restId = restaurantId)

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(

        modifier = androidx.compose.ui.Modifier.padding(16.dp)

    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        ) {
            Column {

                Box(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Color.Gray)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.restaurant3),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,


                    )
                }

                Row(
                    modifier = androidx.compose.ui.Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = androidx.compose.ui.Modifier.weight(1f)
                    )
                    {
                        if (findRest != null) {
                            Text(text = "Restaurant:  ${findRest.name}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                        if (findRest != null) {
                            Text(text = "Location ${findRest.location}",

                                color = Color.Gray)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "4.9")
                            Icon(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = androidx.compose.ui.Modifier.size(16.dp)
                            )
                        }
                        Text(text = "An attractive store with delicious food, local drinks.")
                    }
                    IconButton(onClick = { /* map açılacak */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.map_icon),
                            contentDescription = null,
                            modifier = androidx.compose.ui.Modifier
                                .size(30.dp)

                                .clickable { /* map açılacak */ }

                        )
                    }


                }
            }
        }


        Column {
            Text(text = "Our Menu", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            TabRow(selectedTabIndex = selectedTabIndex) {
                Tab(selected = selectedTabIndex == 0, onClick = { selectedTabIndex = 0 }) {
                    Text(text = "Cake")
                }
                Tab(selected = selectedTabIndex == 1, onClick = { selectedTabIndex = 1 }) {
                    Text(text = "Coffee")
                }
                Tab(selected = selectedTabIndex == 2, onClick = { selectedTabIndex = 2 }) {
                    Text(text = "Chinese")
                }
            }
            when (selectedTabIndex) {
                0 -> {
                    MenuRow(name = "Chocolate Cake", price = "$10")
                    MenuRow(name = "Vanilla Cake", price = "$12")
                    MenuRow(name = "Strawberry Cake", price = "$15")
                }
                1 -> {
                    MenuRow(name = "Espresso", price = "$3")
                    MenuRow(name = "Cappuccino", price = "$4")
                    MenuRow(name = "Latte", price = "$4.5")
                }
                2 -> {
                    MenuRow(name = "Kung Pao Chicken", price = "$10")
                    MenuRow(name = "Sweet and Sour Pork", price = "$12")
                    MenuRow(name = "Fried Rice", price = "$8")
                }
        }

    }
        Column {
            AllComment(filterCom)
        }

}

@Composable
fun MenuItem(name: String, price: String) {

    Row(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name)
        Text(text = price)
    }
}
}
@Composable
fun MenuRow(name: String, price: String) {
    Row(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name)
        Text(text = price)
    }
}

@Composable
fun filterComment(commentList : List<CommentData.Comment> ,restId : String?): List<CommentData.Comment> {
    val filterComments = mutableListOf<CommentData.Comment>()
    for (comment in commentList) {
        if(comment.restaurantId == restId) {
            filterComments.add(comment)
        }
    }
    return filterComments
}

@Composable
fun AllComment(comList: List<CommentData.Comment>) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(comList) { com ->
            cardCom(com)
        }
    }
}

@Composable
fun cardCom(comment : CommentData.Comment) {


    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
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
                        painter = painterResource(id = R.drawable.profileicon),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(end = 8.dp)
                            .height(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(comment.subject, fontWeight = FontWeight.Bold)
                    Text(comment.body, fontWeight = FontWeight.Light)

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


        }
    }
}