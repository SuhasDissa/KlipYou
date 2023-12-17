package net.youapps.converter

import android.app.Application
import net.youapps.converter.backend.util.NotificationHelper

class ConverterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.buildNotificationChannels(this)
    }
}
