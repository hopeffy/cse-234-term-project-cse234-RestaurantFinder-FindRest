import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.restaurantfinder.R
import com.example.restaurantfinder.data.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun Profile_Layout(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().getReference("accounts")
    val coroutineScope = rememberCoroutineScope()
    var fullName by  remember { mutableStateOf(" ") }


    LaunchedEffect(Unit) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            coroutineScope.launch(Dispatchers.IO) {
                try {
                    val snapshot = database.child(userId).get().await()
                    val account = snapshot.getValue(Account::class.java)
                    if (account != null) {
                        fullName = account.fullName
                    }
                } catch (e: Exception) {
                    // to-do

                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.profileicon),
            contentDescription = "Profile photo " ,
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .size(200.dp)

        )
        Spacer(modifier = Modifier.height(6.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "Merhaba, ${fullName}",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 6.dp, start = 40.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ElevatedButton(
                        onClick = { navController.navigate("edit") },
                        modifier = Modifier
                            .height(40.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFC00B)),
                        elevation = ButtonDefaults.buttonElevation(10.dp)
                    ) {
                        Text(
                            text = "EDIT",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { navController.navigate("comments") },
                        modifier = Modifier
                            .height(46.dp)
                            .width(200.dp)
                            .border( width = 1.dp , Color.Gray , shape = RoundedCornerShape(100.dp))
                        ,
                        colors = ButtonDefaults.buttonColors(Color.White)

                    ) {
                        Text(
                            text = "COMMENTS",
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = { /* Handle button click */ },
                        modifier = Modifier
                            .height(46.dp)
                            .width(200.dp)
                            .border( width = 1.dp , Color.Gray , shape = RoundedCornerShape(100.dp))
                        ,
                        colors = ButtonDefaults.buttonColors(Color.White)

                    ) {
                        Text(
                            text = "LANGUAGES",
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { /* Handle button click */ },
                        modifier = Modifier
                            .height(46.dp)
                            .width(200.dp)
                            .border( width = 1.dp , Color.Gray , shape = RoundedCornerShape(100.dp))
                        ,
                        colors = ButtonDefaults.buttonColors(Color.White)

                    ) {
                        Text(
                            text = "DARK MODE",
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    ElevatedButton(
                        onClick = {
                            auth.signOut()
                            navController.navigate("sign-in") },
                        modifier = Modifier
                            .height(40.dp)
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFC00B)),
                        elevation = ButtonDefaults.buttonElevation(10.dp)
                    ) {
                        Text(
                            text = "LOG OUT",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }

    }
}
