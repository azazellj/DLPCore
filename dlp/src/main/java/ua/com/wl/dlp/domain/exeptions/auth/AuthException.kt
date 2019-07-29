package ua.com.wl.dlp.domain.exeptions.auth

import android.content.Context

import ua.com.wl.dlp.R
import ua.com.wl.dlp.data.api.ResponseType
import ua.com.wl.dlp.domain.exeptions.ApiRuntimeException

/**
 * @author Denis Makovskyi
 */

class AuthException(message: String) : ApiRuntimeException(message) {

    override fun getLocalizedMessage(context: Context): String = when (message) {
        ResponseType.TOKEN_REQUIRED -> context.getString(R.string.dlp_error_token_required)
        ResponseType.TOKEN_INVALID -> context.getString(R.string.dlp_error_token_invalid)
        ResponseType.TOKEN_EXPIRED -> context.getString(R.string.dlp_error_token_expired)
        ResponseType.MOBILE_PHONE_REQUIRED -> context.getString(R.string.dlp_error_phone_required)
        ResponseType.MOBILE_PHONE_INVALID -> context.getString(R.string.dlp_error_phone_invalid)
        ResponseType.SMS_DELIVERY_FAILURE -> context.getString(R.string.dlp_error_sms_delivery_failure)
        ResponseType.SMS_RETRIEVAL_ATTEMPTS_EXCEEDED -> context.getString(R.string.dlp_error_sms_retrieval_attempts_exceeded)
        ResponseType.SMS_AUTH_NOT_SUPPORTED_FOR_BUSINESS -> context.getString(R.string.dlp_error_sms_auth_not_supported_for_business)
        ResponseType.PASSWORD_REQUIRED -> context.getString(R.string.dlp_error_password_required)
        ResponseType.CONSUMER_NOT_FOUND -> context.getString(R.string.dlp_error_consumer_not_found)
        ResponseType.CONSUMER_ACCOUNT_DISABLED -> context.getString(R.string.dlp_error_consumer_account_disabled)
        ResponseType.CONSUMER_CREDENTIAL_INVALID -> context.getString(R.string.dlp_error_consumer_credential_invalid)
        else -> super.getLocalizedMessage(context)
    }
}