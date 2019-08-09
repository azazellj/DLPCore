package ua.com.wl.dlp.domain.exeptions

import android.content.Context

/**
 * @author Denis Makovskyi
 */

abstract class CoreException(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {

    abstract fun getLocalizedMessage(context: Context): String
}