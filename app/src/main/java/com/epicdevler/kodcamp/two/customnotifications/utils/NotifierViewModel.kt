package com.epicdevler.kodcamp.two.customnotifications.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epicdevler.kodcamp.two.customnotifications.R
import com.epicdevler.kodcamp.two.customnotifications.data.NotificationContents
import com.epicdevler.kodcamp.two.customnotifications.data.models.NotificationContentsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.random.Random

@HiltViewModel
class NotifierViewModel @Inject constructor(
    private val context: Context,
    private val notificationBuilder: NotificationCompat.Builder,
    @Named("ExpandedNotifierView")
    private val expandedNotifierView: RemoteViews,
    @Named("CollapsedNotifierView")
    private val collapsedNotifierView: RemoteViews,
) : ViewModel() {

    private val notification_title = R.id.notification_title
    private val notification_content = R.id.notification_content
    val notification_imgView = R.id.notification_imgView

    @SuppressLint("RestrictedApi")
    suspend fun showNotification() {
        val notification = notificationBuilder
        withContext(Dispatchers.Main) {
            content.collect {
                Log.e(Constants.TAG, "showNotification: $it")
                expandedNotifierView.setTextViewText(notification_title, it.title)
                expandedNotifierView.setTextViewText(
                    notification_content,
                    context.resources.getString(it.content)
                )
                expandedNotifierView.setImageViewResource(notification_imgView, it.infoGraphic)

                collapsedNotifierView.setTextViewText(notification_title, it.title)
                collapsedNotifierView.setTextViewText(
                    notification_content,
                    context.resources.getString(it.content)
                )
                val notifierId = 0
                with(NotificationManagerCompat.from(context)) {
                    notify(notifierId, notification.build())
                }
            }
        }
    }

    private val _content: MutableSharedFlow<NotificationContentsModel> = MutableSharedFlow()
    val content = _content.asSharedFlow()

    fun getData() {
        val remoteData = NotificationContents.getRemoteData()
        viewModelScope.launch(Dispatchers.IO) {
            _content.emit(
                remoteData[Random.nextInt(1, remoteData.size)]
            )
            showNotification()
        }
    }

}