package com.alejandro.domain.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import java.net.UnknownHostException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.NumberFormat
import java.util.Locale

fun encryptSHA512(password: String, salt: String): String {
    var generatedPassword = ""
    try {
        val md = MessageDigest.getInstance("SHA-512")
        md.update(salt.toByteArray())
        val data = md.digest(password.toByteArray())
        val sb = StringBuilder()
        for (i in data.indices) {
            sb.append(((data[i].toInt() and 0xff) + 0x100).toString(16).substring(1))
        }
        generatedPassword = sb.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return generatedPassword
}

fun formatNumber(number: Double, decimalPlaces: Int = 2): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    numberFormat.minimumFractionDigits = decimalPlaces
    numberFormat.maximumFractionDigits = decimalPlaces
    return numberFormat.format(number)
}

fun Throwable.isUnknownHostException(): Boolean {
    return (this is UnknownHostException)
}

fun sharedString(context: Context, value: String) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, value)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(intent, null)
    context.startActivity(shareIntent)
}

fun copyToClipboard(context: Context, text: String, label: String = String()) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(label, text)
    clipboardManager.setPrimaryClip(clipData)
}
