plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")

    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}
apply {
    plugin("kotlin-android")
}
android {
    namespace = "com.nativemobilebits.globe"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.nativemobilebits.globe"
        minSdk = 24
        targetSdk = 32
        versionCode = Versions.versionCode
        versionName = Versions.versionName
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
    sourceSets {
        getByName("main").java.srcDirs("build/generated/source/navigation-args")
    }
    lintOptions.isAbortOnError = false
    lintOptions.isCheckReleaseBuilds = false
    packagingOptions {
        exclude("META-INF/*")
    }
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.appcompat)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.materialDesign)
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

    implementation(Dependencies.pixplicity)
    
    // Gson
    implementation(Dependencies.gson)
    implementation(Dependencies.gsonConverter)
    // Lifecycle
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.lifecycleLiveDataKtx)


    implementation(project(Modules.datasource))


    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.junitExtension)
    androidTestImplementation(TestLibs.espressoCore)

    testImplementation(TestLibs.coreTesting)
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.junitExtension)
    testImplementation(TestLibs.mockitoKotlin)
    androidTestImplementation(TestLibs.espressoCore)

    implementation(Dependencies.splashAPI)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.materialDesign)

//    implementation(Dependencies.glide)
//    implementation(Dependencies.glideKapt)
//    implementation(Dependencies.glideVector)

    implementation(Dependencies.navComponentUi)
    implementation(Dependencies.navComponentFragment)



}
repositories {
    maven { setUrl("https://jitpack.io") }
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    mavenCentral()
}