package ua.com.wl.dlp.utils

import android.content.Context

import ua.com.wl.dlp.R
import ua.com.wl.dlp.domain.exeptions.CoreException

/**
 * @author Denis Makovskyi
 */

fun localizeError(context: Context, error: Throwable?): String =
    when (error) {
        is CoreException -> error.getLocalizedMessage(context)
        else -> context.getString(R.string.dlp_error_runtime)
    }