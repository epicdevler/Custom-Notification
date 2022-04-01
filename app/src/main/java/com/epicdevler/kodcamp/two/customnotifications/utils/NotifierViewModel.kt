package com.epicdevler.kodcamp.two.customnotifications.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import com.epicdevler.kodcamp.two.customnotifications.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NotifierViewModel @Inject constructor(
    private val context: Context,
    private val notificationBuilder: NotificationCompat.Builder
    ) : ViewModel() {

    fun showNotification() {
        val notification = notificationBuilder
        // Show the notification with notificationId.
        val uniqueNotificationId = 0
        with(NotificationManagerCompat.from(context)) {
            notify(uniqueNotificationId, notification.build())
        }
    }

}