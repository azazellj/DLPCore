package ua.com.wl.dlp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * @author Denis Makovskyi
 */

fun getQueryParam(url: String, key: String): String? {
    if (url.contains("?")) {
        val params = url.substring(url.indexOf("?") + 1).split("&")
        for (param in params) {
            val keyVal = param.split("=")
            if (keyVal[0] == key) return keyVal[1]
        }
    }
    return null
}

internal fun sendBroadcastMessage(context: Context, action: String, extras: Bundle? = null) {
    val intent = Intent().apply {
        setAction(action)
        extras?.only { putExtras(it) }
    }
    context.sendBroadcast(intent)
}