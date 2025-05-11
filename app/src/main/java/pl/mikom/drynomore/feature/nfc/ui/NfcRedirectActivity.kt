package pl.mikom.drynomore.feature.nfc.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

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
        }
    }

    companion object {

        private val TAG = NfcRedirectActivity::class.java.simpleName
    }
}


