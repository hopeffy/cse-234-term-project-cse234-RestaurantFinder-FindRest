package com.example.restaurantfinder

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.restaurantfinder.data.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.jetbrains.annotations.ApiStatus.Experimental


@Composable
fun ResetPassword(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().getReference("accounts")
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "DID YOU FORGET",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 6.dp , start = 18.dp)
                )
                Text(
                    text = "PASSWORD?",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 80.dp , start = 54.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "E-mail") },
                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Enter your e-mail address",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(
                        onClick = {
                            if (email.isNotEmpty()) {
                                coroutineScope.launch(IO) {
                                    try {
                                        auth.sendPasswordResetEmail(email).await()
                                        errorMessage = "A password reset link has been sent to your email"
                                    } catch (e: FirebaseAuthException) {
                                        errorMessage = "Failed to send password reset email: ${e.message}"
                                    }
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()

                                }
                            } else {
                                Toast.makeText(context, "Please enter your email", Toast.LENGTH_LONG).show()

                            }

                            navController.navigate("sign-in") },
                        modifier = Modifier
                            .height(52.dp)
                            .width(200.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFC00B)),
                        elevation = ButtonDefaults.buttonElevation(10.dp)
                    ) {
                        Text(
                            text = "RESET",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}