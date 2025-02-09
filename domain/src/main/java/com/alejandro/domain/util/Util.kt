package com.alejandro.domain.util

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