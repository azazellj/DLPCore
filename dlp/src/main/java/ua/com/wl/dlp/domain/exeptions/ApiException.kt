package ua.com.wl.dlp.domain.exeptions

import android.content.Context

/**
 * @author Denis Makovskyi
 */

abstract class ApiException(message: String? = null) : Exception(message) {

    abstract fun getLocalizedMessage(context: Context): String
}