@file:Suppress("UNUSED_EXPRESSION")

val annotationProcessor: Unit = Unit


val implementation: Unit = Unit



plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "br.fepi.agendadecontatos"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.fepi.agendadecontatos"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    annotationProcessor(libs.room.compiler)

    // Room dependencies
    implementation; "androidx.room:room-runtime:2.6.1"
    annotationProcessor; "androidx.room:room-compiler:2.6.1"

    // Optional - Kotlin Extensions and Coroutines support for Room
    implementation; "androidx.room:room-ktx:2.6.1"
}
