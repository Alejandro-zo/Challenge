import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.alejandro.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        val envFile = project.rootProject.file("enviroments.env")
        val envProperties = Properties()
        envProperties.load(envFile.inputStream())

        val baseUrlRelease = envProperties.getProperty("BASE_URL_RELEASE")
        val baseUrlDebug = envProperties.getProperty("BASE_URL_DEBUG")
        val salt = envProperties.getProperty("SALT")
        val passwordUser1 = envProperties.getProperty("PASSWORD_USER_1")
        val passwordUser2 = envProperties.getProperty("PASSWORD_USER_2")
        val passwordUser3 = envProperties.getProperty("PASSWORD_USER_3")


        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"$baseUrlRelease\"")
            buildConfigField("String", "SALT", "\"$salt\"")
            buildConfigField("String", "PASSWORD_USER_1", "\"$passwordUser1\"")
            buildConfigField("String", "PASSWORD_USER_2", "\"$passwordUser2\"")
            buildConfigField("String", "PASSWORD_USER_3", "\"$passwordUser3\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"$baseUrlDebug\"")
            buildConfigField("String", "SALT", "\"$salt\"")
            buildConfigField("String", "PASSWORD_USER_1", "\"$passwordUser1\"")
            buildConfigField("String", "PASSWORD_USER_2", "\"$passwordUser2\"")
            buildConfigField("String", "PASSWORD_USER_3", "\"$passwordUser3\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //ktor
    implementation(libs.ktor.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.json)
    implementation(libs.kotlinx.serialization.json)

    // chucker
    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

    // room
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.slf4j.logback)
    testImplementation(libs.kotlin.faker)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // modules
    implementation(project(":domain"))
}