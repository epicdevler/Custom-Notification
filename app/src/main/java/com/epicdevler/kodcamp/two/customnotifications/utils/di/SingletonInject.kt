package com.epicdevler.kodcamp.two.customnotifications.utils.di

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
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


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
        packageName: String
    ): NotificationCompat.Builder {
        // Layouts for the custom notification
        val notificationLayout = RemoteViews(packageName, R.layout.notification_collapsed)
        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.notification_expanded)

        return NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(com.google.android.material.R.drawable.abc_ic_clear_material)
            .setColor(ResourcesCompat.getColor(context.resources, R.color.purple_700, null))
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setCustomBigContentView(notificationLayoutExpanded)
    }

}