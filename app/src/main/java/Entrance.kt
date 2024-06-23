import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.restaurantfinder.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Entrance(navController : NavHostController){

    val auth = FirebaseAuth.getInstance()

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.finding),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
                .clickable {
                    if(auth.currentUser == null) {
                        navController.navigate("sign_in")
                    } else {
                        navController.navigate("home")
                    }
                     }
        )
    }
}