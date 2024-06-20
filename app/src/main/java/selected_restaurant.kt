import android.view.MenuItem
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

import androidx.compose.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.lang.reflect.Modifier

@Composable
fun SelectedRestaurant(navController: NavHostController) {

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
                        painter = painterResource(id = R.drawable.restaurant),
                        contentDescription = null
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
                        Text(text = "Restaurant:  ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text(text = "Location", color = Color.Gray)
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
                    IconButton(onClick = { /* TODO: Handle map click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.map_icon),
                            contentDescription = null,
                            modifier = androidx.compose.ui.Modifier.size(30.dp)
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
