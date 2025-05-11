package pl.mikom.drynomore.feature.nfc.ui

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Build

object NfcUtil {

    fun readPayload(intent: Intent): String? {
        return if (intent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            readMessage(intent)
                .firstOrNull()
                ?.records
                ?.firstOrNull()
                ?.payload
                ?.toString(Charsets.UTF_8)
        } else {
            null
        }
    }

    private fun readMessage(intent: Intent): List<NdefMessage> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES,
                NdefMessage::class.java
            )?.toList()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.map {
                it as NdefMessage
            }
        } ?: emptyList()
    }
}
