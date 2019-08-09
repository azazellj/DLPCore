package ua.com.wl.dlp.domain.exeptions

import java.io.IOException
import javax.net.ssl.SSLException

import android.content.Context

import com.google.gson.JsonParseException

import ua.com.wl.dlp.R

/**
 * @author Denis Makovskyi
 */

open class ApiException(message: String? = null, cause: Throwable? = null) : CoreRuntimeException(message, cause) {

    override fun getLocalizedMessage(context: Context): String =
        when (cause) {
            is IOException -> context.getString(R.string.dlp_error_io)
            is SSLException -> context.getString(R.string.dlp_error_ssl)
            is JsonParseException -> context.getString(R.string.dlp_error_json)
            else -> super.getLocalizedMessage(context)
        }
}