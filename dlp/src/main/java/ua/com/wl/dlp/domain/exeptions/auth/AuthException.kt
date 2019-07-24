package ua.com.wl.dlp.domain.exeptions.auth

import android.content.Context

import ua.com.wl.dlp.domain.exeptions.ApiRuntimeException

/**
 * @author Denis Makovskyi
 */

class AuthException(message: String) : ApiRuntimeException(message) {

    override fun getLocalizedMessage(context: Context): String = when (message) {
        "" -> ""
        else -> super.getLocalizedMessage(context)
    }
}