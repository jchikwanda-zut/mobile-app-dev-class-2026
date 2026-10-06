package com.zut.campusconnect.ui.profile

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zut.campusconnect.ui.components.LabledTextField

@Composable
fun ProfileSetupScreen(
    onComplete: (name: String, program: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var program by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Campus Connect",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "All information will be stored on device only - no account needed",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(24.dp))
        LabledTextField(
            value = name,
            onValueChange = { name = it },
            label = "Your name"
        )
        Spacer(modifier = Modifier.height(12.dp))
        LabledTextField(
            value = program,
            onValueChange = { program = it },
            label = "Your program"
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                onComplete(name, program)
            },
            enabled = name.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Started")
        }
    }
}