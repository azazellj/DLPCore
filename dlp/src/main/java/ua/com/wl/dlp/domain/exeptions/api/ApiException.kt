package ua.com.wl.dlp.domain.exeptions.api

import android.content.Context
import ua.com.wl.dlp.common.R
import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException
import ua.com.wl.dlp.domain.exeptions.api.ApiErrorType.*
import ua.com.wl.dlp.utils.getApiErrorType

open class ApiException(
    type: String? = null,
    cause: Throwable? = null
) : CoreRuntimeException(type, cause) {

    override fun getLocalizedMessage(context: Context): String {
        return when (getApiErrorType(cause)) {
            SSL_CERTIFICATE -> context.getString(R.string.dlp_error_network_ssl)
            RESPONSE_PARSING -> context.getString(R.string.dlp_error_network_json)
            NETWORK_CONNECTION -> context.getString(R.string.dlp_error_network_io)
            SERVER_UNREACHABLE -> context.getString(R.string.dlp_error_server_unreachable)
            INTERNAL_SERVER_ERROR -> context.getString(R.string.dlp_error_server)
            else -> super.getLocalizedMessage(context)
        }
    }
}