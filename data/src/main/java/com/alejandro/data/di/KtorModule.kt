package com.alejandro.data.di

import android.content.Context
import com.alejandro.data.BuildConfig
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {
    @Singleton
    @Provides
    fun provideKtor(
        @ApplicationContext context: Context,
        json: Json,
    ): HttpClient = HttpClient(OkHttp) {
        expectSuccess = false
        engine {
            addInterceptor(ChuckerInterceptor(context))
            config { certificatePinner(SslPinning.makePinningCertificate()) }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 1000 * 1 * 60
        }
        install(ContentNegotiation) { json(json) }
        install(DefaultRequest) { url(BuildConfig.BASE_URL) }
    }

    @Provides
    @Singleton
    fun provideJson() = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
}
