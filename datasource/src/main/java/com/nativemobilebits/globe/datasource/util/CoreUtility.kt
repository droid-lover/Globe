package com.nativemobilebits.globe.datasource.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.DrawableCompat
import com.nativemobilebits.globe.datasource.R
import com.nativemobilebits.globe.datasource.remote.entity.ErrorModel
import com.squareup.moshi.JsonReader

object CoreUtility {

    @JvmStatic
    fun isInternetConnected(context: Context): Boolean {

        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result


    }

    fun checkNetworkAndToast(context: Context): Boolean {
        return if (!isInternetConnected(context)) {
            Toast.makeText(context, context.getString(R.string.check_internet), Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }

    }

    inline fun JsonReader.readArray(body: () -> Unit) {
        beginArray()
        while (hasNext()) {
            body()
        }
        endArray()
    }
}