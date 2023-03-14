plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")

    id("dagger.hilt.android.plugin")
}
apply {
    plugin("kotlin-android")
}

android {
    namespace = "com.nativemobilebits.globe.datasource"
    compileSdk = 32

    defaultConfig {
        minSdk = 24
        targetSdk = 32
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }


    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Hilt
    implementation(Dependencies.hiltCore)
    implementation(Dependencies.hiltCommon)
    kapt(Dependencies.hiltDaggerAndroidCompiler)
    kapt(Dependencies.hiltCompiler)

    // Okhttp
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLogging)
    // Retrofit and Moshi
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodeGen)
    // Gson
    implementation(Dependencies.gson)
    implementation(Dependencies.gsonConverter)

    // Room
    api(Dependencies.roomRuntime)
    api(Dependencies.roomKts)
    kapt(Dependencies.roomCompiler)


}
repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    mavenCentral()
}