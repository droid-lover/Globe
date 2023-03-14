// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven {
            url  = uri("http://dl.bintray.com/countly/maven")
            isAllowInsecureProtocol = true
        }
        maven {
            url = uri("https://androidx.dev/snapshots/builds/6543454/artifacts/repository/")
        }
        maven {
            url = uri("https://developer.biovotion.com:8443/repository/maven-releases/")
        }
        flatDir {
            dirs("libs")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}