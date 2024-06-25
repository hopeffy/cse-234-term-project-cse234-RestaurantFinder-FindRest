package com.example.restaurantfinder

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.restaurantfinder.data.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun SignInScreen(navController: NavHostController) {

    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign In", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text(text = "E-mail") })
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text(text = "Password") })
        Spacer(modifier = Modifier.height(20.dp))

        TextButton(onClick = {navController.navigate("reset")}) {

            Text(text = "Forget Password?" )
        }

        
        ElevatedButton(
            onClick = {
                coroutineScope.launch {
                    try {
                        auth.signInWithEmailAndPassword(email, password).await()
                        navController.navigate("home")

                    } catch (e: FirebaseAuthException) {
                        // Handle user not found error
                        if (e.errorCode == "user-not-found") {
                            Toast.makeText(navController.context, "User not found. Please sign up.", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(navController.context, isEmailInvalidOrPasswordWrong(e), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(navController.context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .height(40.dp)
                .width(250.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFC00B)),
            elevation = ButtonDefaults.buttonElevation(10.dp)
        ) {
            Text(text = "SIGN IN", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate("sign_up") }) {
            Text(text = "SIGN UP")
        }
    }

}

private fun isEmailInvalidOrPasswordWrong(e: FirebaseAuthException): String {
    return if (e.errorCode == "invalid-email" || e.errorCode == "wrong-password") {
        "Invalid email or password."
    } else {
        e.message ?: "An error occurred."
    }
}