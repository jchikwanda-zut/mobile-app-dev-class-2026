package com.zut.campusconnect.ui.profile

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.zut.campusconnect.R
import com.zut.campusconnect.ReportIssueActivity

/*
* Instructors Android Device Specs
* Android Version: 11
* API Level of Android Version above: 30
* */
fun showTestNotification(context: Context) {
    val channelId = "general_notifications"

    // Create Channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            "General Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        context
            .getSystemService(NotificationManager::class.java)
            .createNotificationChannel(channel)
    }

    // Lets create intent
    val intent = Intent(
        context,
        ReportIssueActivity::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        1,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )


    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle("Notifications are on")
        .setContentText("This is a notification from campus connect")
        .setCategory(NotificationCompat.CATEGORY_SOCIAL)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    if (
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
        ) {
        NotificationManagerCompat.from(context).notify(1, notification)
    }
}