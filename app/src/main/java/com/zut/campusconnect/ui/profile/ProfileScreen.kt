package com.zut.campusconnect.ui.profile

import android.Manifest
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zut.campusconnect.R
import com.zut.campusconnect.ReportIssueActivity
import com.zut.campusconnect.ui.components.SettingsToggleRow

@Composable
fun ProfileScreen(name: String, program: String) {
    val context = LocalContext.current;
    var notificationsEnabled by remember { mutableStateOf(false) }
    var eventRemindersEnabled by remember { mutableStateOf(false) }
    // Handle Permission
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        granted ->
        notificationsEnabled = granted
        if (granted) {
            showTestNotification(context)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(80.dp).border(2.dp, Color.Black, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.take(1).uppercase(),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = program,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier=Modifier.height(24.dp))
        Button(
            onClick = {
                Log.i("LOG", "Button Clicked")
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Check out CampusConnect")
                }
                context.startActivity(Intent.createChooser(shareIntent, "Share Via"))
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tell a friend")
        }

        // Explicit Intent Button Trigger
        Button(
            onClick = {
                val intent = Intent(context, ReportIssueActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Report an Issue")
        }

        // Notification Button
//        Button(
//            onClick = {
//                  if (Build.VERSION.SDK_INT >= 33) {
//                      permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//                  } else {
//                      showTestNotification(context)
//                  }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Test Notification")
//        }

        SettingsToggleRow(
            icon = R.drawable.ic_notification,
            label = "Notifications",
            checked = notificationsEnabled,
            onCheckedChange = {
                    checked ->
                if (checked) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    } else {
                        notificationsEnabled = true
                        showTestNotification(context)
                    }
                } else {
                    notificationsEnabled = false
                }
            }
        )
        SettingsToggleRow(
            icon = R.drawable.ic_android_black_24dp,
            label = "Event Reminders",
            checked = eventRemindersEnabled,
            onCheckedChange = { eventRemindersEnabled = it }
        )
    }
}