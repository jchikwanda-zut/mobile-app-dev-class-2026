package com.zut.campusconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zut.campusconnect.ui.components.Incrementor

class ReportIssueActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Report Issue",
                    style = MaterialTheme.typography.titleLarge
                )
                // Incrementor()
                Button(
                    onClick = {
                        finish()
                    }
                ) {
                    Text(
                        text = "Close"
                    )
                }
            }
        }
    }
}