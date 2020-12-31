package ua.com.wl.dlp.utils

import java.io.IOException
import java.net.HttpURLConnection.HTTP_UNAVAILABLE
import java.net.HttpURLConnection.HTTP_BAD_GATEWAY
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR

import javax.net.ssl.SSLException

import android.content.Context
import com.squareup.moshi.JsonDataException

import retrofit2.HttpException

import ua.com.wl.dlp.common.R
import ua.com.wl.dlp.domain.exeptions.CoreException
import ua.com.wl.dlp.domain.exeptions.api.ApiErrorType
import ua.com.wl.dlp.domain.exeptions.api.ApiErrorType.*
import ua.com.wl.dlp.domain.exeptions.api.ApiException

/**
 * @author Denis Makovskyi
 */

fun localizeError(context: Context, error: Throwable?): String {
    return when (error) {
        is CoreException -> error.getLocalizedMessage(context)
        else -> context.getString(R.string.dlp_error_runtime)
    }
}

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

fun getApiErrorType(cause: Throwable?): ApiErrorType? {
    return when(cause) {
        is IOException -> NETWORK_CONNECTION
        is SSLException -> SSL_CERTIFICATE
        is JsonDataException -> RESPONSE_PARSING
        else -> {
            if (cause is HttpException) {
                when(cause.code()) {
                    HTTP_INTERNAL_ERROR -> INTERNAL_SERVER_ERROR
                    HTTP_BAD_GATEWAY, HTTP_UNAVAILABLE-> SERVER_UNREACHABLE
                    else -> null
                }
            } else null
        }
    }
}

fun getExceptionApiErrorType(error: Throwable): ApiErrorType? {
    return if (error is ApiException) {
        getApiErrorType(error.cause)
    } else null
}

fun getApiExceptionErrorType(exception: ApiException): ApiErrorType? {
    return getExceptionApiErrorType(exception)
}