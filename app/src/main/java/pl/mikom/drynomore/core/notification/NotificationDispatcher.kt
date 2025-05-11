package pl.mikom.drynomore.core.notification

import android.app.PendingIntent

interface NotificationDispatcher {

    fun post(data: NotificationData)
}

data class NotificationData(
    val notifyId: Int,
    val title: String,
    val message: String = "",
    val pendingIntent: PendingIntent? = null
)
