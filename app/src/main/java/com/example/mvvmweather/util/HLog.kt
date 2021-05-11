package com.example.mvvmweather.util

import android.util.Log
import com.example.mvvmweather.BuildConfig


/**
 * Developed by Hasham.Tahir on 1/16/2017.
 */

object HLog {

    private val TAG = HLog::class.java.simpleName
    private val EMPTY = ""

    private fun e(tag: String, format: String, vararg args: String): Int {
        return Log.e(tag, format(format, *args))
    }

    fun e(tag: String, msg: String) {
        if (BuildConfig.BUILD_TYPE == ("debug")) {
            val maxLogSize = 1000
            for (i in 0..msg.length / maxLogSize) {
                val start = i * maxLogSize
                var end = (i + 1) * maxLogSize
                end = if (end > msg.length) msg.length else end
                Log.e(tag, msg.substring(start, end))
            }
        }
    }

    fun e(tag: String, msg: String, e: Throwable): Int {
        return Log.e(tag, msg, e)
    }

    fun e(tag: String, format: String, e: Throwable, vararg args: String): Int {
        return Log.e(tag, format(format, *args), e)
    }

    private fun format(format: String, vararg args: Any): String {
        return try {
            String.format(format, *args)
        } catch (e: RuntimeException) {
            e(TAG, "format error. reason=%s, format=%s", e.message as String, format)
            String.format(EMPTY, format)
        }

    }
}
