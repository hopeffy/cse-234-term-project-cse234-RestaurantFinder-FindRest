package com.example.restaurantfinder

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(navController: NavHostController) {
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = fullName, onValueChange = { fullName = it },
            label = { Text(text = "Full Name") })
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(value = phone, onValueChange = { phone = it },
            label = { Text(text = "Phone") })
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(value = email, onValueChange = { email = it },
            label = { Text(text = "E-mail") })
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(value = password, onValueChange = { password = it },
            label = { Text(text = "Password") })
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") })
        Spacer(modifier = Modifier.height(20.dp))

        ElevatedButton(
            onClick = { navController.navigate("sign_in") },
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .height(40.dp)
                .width(250.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFC00B)),
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Text(text = "CREATE", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate("sign_in") }) {
            Text(text = "SIGN IN")
        }
    }
}
