package com.alejandro.domain.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

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
