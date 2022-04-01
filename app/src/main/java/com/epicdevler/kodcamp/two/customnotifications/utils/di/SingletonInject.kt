package com.epicdevler.kodcamp.two.customnotifications.utils.di

import android.app.Notification
import android.content.Context
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import com.epicdevler.kodcamp.two.customnotifications.R
import com.epicdevler.kodcamp.two.customnotifications.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
object SingletonInject {


    @Provides
    @ViewModelScoped
    fun provideContext(
        @ApplicationContext
        context: Context
    ) : Context = context

    @Provides
    @ViewModelScoped
    fun providePackageName(
        context: Context
    ): String = context.packageName

    @Provides
    @ViewModelScoped
    fun provideNotifierBuilder(
        context: Context,
        packageName: String,
        @Named("ExpandedNotifierView")
        expandedNotifierView: RemoteViews,
        @Named("CollapsedNotifierView")
        collapsedNotifierView: RemoteViews,
    ): NotificationCompat.Builder {
        // Layouts for the custom notification

        return NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setColor(ResourcesCompat.getColor(context.resources, R.color.purple_700, null))
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setCustomContentView(collapsedNotifierView)
            .setCustomBigContentView(expandedNotifierView)
    }

    @Provides
    @ViewModelScoped
    @Named("ExpandedNotifierView")
    fun provideExpandedNotifierView(
        packageName: String,
    ): RemoteViews = RemoteViews(packageName, R.layout.notification_expanded)

    @Provides
    @ViewModelScoped
    @Named("CollapsedNotifierView")
    fun provideCollapsedNotifierView(
        packageName: String,
    ): RemoteViews = RemoteViews(packageName, R.layout.notification_collapsed)

}