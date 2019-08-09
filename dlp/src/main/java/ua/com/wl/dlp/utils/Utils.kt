package ua.com.wl.dlp.utils

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