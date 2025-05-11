package pl.mikom.drynomore.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import pl.mikom.drynomore.R

class NotificationDispatcherImpl(
    private val context: Context
) : NotificationDispatcher {

    override fun post(data: NotificationData) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(NFC_CHANNEL_ID, NFC_CHANNEL_NAME, IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, NFC_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(data.title)
            .setContentText(data.message)
            .setContentIntent(data.pendingIntent)
            .setPriority(PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        manager.notify(data.notifyId, notification)
    }

    companion object {

        private const val NFC_CHANNEL_ID = "nfc_channel"
        private const val NFC_CHANNEL_NAME = "NFC Notifications"
    }
}