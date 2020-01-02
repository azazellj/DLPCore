package ua.com.wl.dlp.utils

import java.util.*
import java.text.SimpleDateFormat

import android.content.Context

import ua.com.wl.dlp.R
import ua.com.wl.dlp.domain.exeptions.CoreException

/**
 * @author Denis Makovskyi
 */

fun localizeError(context: Context, error: Throwable?): String {
    return when (error) {
        is CoreException -> error.getLocalizedMessage(context)
        else -> context.getString(R.string.dlp_error_runtime)
    }
}

internal fun now(pattern: String, locale: Locale = Locale.getDefault()): String {
    val dateFormat = SimpleDateFormat(pattern, locale)
    return dateFormat.format(Date(System.currentTimeMillis()))
}