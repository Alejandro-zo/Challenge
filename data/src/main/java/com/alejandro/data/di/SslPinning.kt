package com.alejandro.data.di

import okhttp3.CertificatePinner

object SslPinning {

    fun makePinningCertificate(): CertificatePinner {
        val domain = "*.redcol.gasonet.com.pe"
        val hashes: List<String> = listOf()
        val sslPinner = CertificatePinner.Builder()
        hashes.forEach { hash ->
            sslPinner.add(domain, hash)
        }
        return sslPinner.build()
    }
}
