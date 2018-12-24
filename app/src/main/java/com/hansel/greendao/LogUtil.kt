package com.hansel.greendao

import android.util.Log

object LogUtil {
    private val LOG_OPEN = BuildConfig.DEBUG
    private const val TAG = "liyang"

    fun d(msg: String) {
        if (LOG_OPEN)
            Log.d(TAG, msg)
    }
}
