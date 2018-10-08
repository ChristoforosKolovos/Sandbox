package com.example.christoforos.sandbox.presentation.utils

import android.content.Context
import java.util.*


fun getLocale(): String {
    return if (Locale.getDefault().country != null && Locale.getDefault().country.length > 0)
        Locale.getDefault().language + "-" + Locale.getDefault().country
    else Locale.getDefault().language
}

fun getDevice() = android.os.Build.DEVICE + "/" + android.os.Build.MODEL


fun getOSVersion(): String {
    return try {
        android.os.Build.VERSION.SDK_INT.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        99.toString()
    }
}

fun getApplicationVersion(context: Context): String {
    return try {
        context.packageManager.getPackageInfo(context.packageName, 0).versionName
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}