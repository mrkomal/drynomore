package pl.mikom.drynomore.feature.nfc.ui

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import pl.mikom.drynomore.MainActivity
import pl.mikom.drynomore.R
import pl.mikom.drynomore.core.notification.NotificationData
import pl.mikom.drynomore.core.notification.NotificationDispatcherImpl

class NfcRedirectActivity : ComponentActivity() {

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleNdefAction(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleNdefAction(intent)
        finish()
    }

    private fun handleNdefAction(intent: Intent) {
        val nfcPayload = NfcUtil.readPayload(intent)
        nfcPayload?.let {
            Log.d(TAG, it)
            NotificationDispatcherImpl(this).post(
                NotificationData(
                    notifyId = NFC_NOTIFY_ID,
                    title = getString(R.string.nfc_notification_title),
                    pendingIntent = nfcPendingIntent
                )
            )
        }
    }

    private val nfcPendingIntent: PendingIntent
        get() {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            return PendingIntent.getActivity(
                this,
                REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }

    companion object {

        private val TAG = NfcRedirectActivity::class.java.simpleName
        private const val NFC_NOTIFY_ID = 1001
        private const val REQUEST_CODE = 0
    }
}
