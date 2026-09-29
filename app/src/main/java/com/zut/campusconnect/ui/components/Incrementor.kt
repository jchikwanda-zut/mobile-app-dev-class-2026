package com.zut.campusconnect.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun Incrementor() {
    // const [value, setValue] = useState("")
    // const value = ""
    // var count = 0
    var count by remember {
        mutableStateOf(0)
    }
    Column() {
        Text(
            text = "Count: ${count}"
        )
        Button(
            onClick = {
                count++
                Log.i("INCREMENT", "${count}")
            }
        ) {
            Text("Increase")
        }
        Button(
            onClick = {
                count--
                Log.i("INCREMENT", "${count}")
            }
        ) {
            Text("Decrease")
        }
    }
}