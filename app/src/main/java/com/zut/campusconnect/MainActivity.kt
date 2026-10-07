package com.zut.campusconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.zut.campusconnect.ui.profile.ProfileScreen
import com.zut.campusconnect.ui.profile.ProfileSetupScreen
import com.zut.campusconnect.ui.theme.CampusConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Install Splashscreen
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CampusConnectTheme {
                // Change this back to false later
                var setupComplete by remember {
                    mutableStateOf(true)
                }
                var userName by remember {
                    mutableStateOf("Jacob")
                }
                var userProgram by remember {
                    mutableStateOf("Electrical Engineer")
                }

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (!setupComplete) {
                        ProfileSetupScreen(
                            onComplete = { name, program ->
                                userName = name
                                userProgram = program
                                setupComplete = true
                            }
                        )
                    } else {
                        ProfileScreen(userName, userProgram)
                    }
                }
            }
        }
    }
}