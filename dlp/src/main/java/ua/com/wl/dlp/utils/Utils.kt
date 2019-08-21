package ua.com.wl.dlp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * @author Denis Makovskyi
 */

internal fun getQueryParam(url: String, key: String): String? {
    if (url.contains("?")) {
        val params = url.substring(url.indexOf("?") + 1).split("&")
        for (param in params) {
            val keyVal = param.split("=")
            if (keyVal[0] == key) return keyVal[1]
        }
    }
    return null
}

internal fun createBroadcastMessage(context: Context, action: String, extras: Bundle? = null) =
    Intent().apply {
        setAction(action)
        addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        extras?.only { putExtras(it) }
    }.only { context.sendBroadcast(it) }