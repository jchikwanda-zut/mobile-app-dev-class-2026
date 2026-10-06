package com.zut.campusconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
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
                var setupComplete by remember {
                    mutableStateOf(false)
                }
                var userName by remember {
                    mutableStateOf("")
                }
                var userProgram by remember {
                    mutableStateOf("")
                }

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