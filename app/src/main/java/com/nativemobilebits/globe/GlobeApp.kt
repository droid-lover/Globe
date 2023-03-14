package com.nativemobilebits.globe

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class GlobeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: GlobeApp? = null
    }
}