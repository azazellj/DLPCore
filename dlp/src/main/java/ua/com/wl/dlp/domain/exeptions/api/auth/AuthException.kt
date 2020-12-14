package ua.com.wl.dlp.domain.exeptions.api.auth

import android.content.Context

import ua.com.wl.dlp.common.R
import ua.com.wl.dlp.data.api.responses.ResponseType
import ua.com.wl.dlp.domain.exeptions.api.ApiException

class AuthException(type: String?, cause: Throwable?) : ApiException(type, cause) {
    override fun getLocalizedMessage(context: Context): String {
        return when (message) {
            ResponseType.TOKEN_REQUIRED -> context.getString(R.string.dlp_error_token_required)
            ResponseType.TOKEN_INVALID -> context.getString(R.string.dlp_error_token_invalid)
            ResponseType.TOKEN_EXPIRED -> context.getString(R.string.dlp_error_token_expired)
            ResponseType.MOBILE_PHONE_REQUIRED -> context.getString(R.string.dlp_error_phone_required)
            ResponseType.MOBILE_PHONE_INVALID -> context.getString(R.string.dlp_error_phone_invalid)
            ResponseType.MOBILE_PHONE_REGISTERED -> context.getString(R.string.dlp_error_phone_registered)
            ResponseType.SMS_DELIVERY_FAILURE -> context.getString(R.string.dlp_error_sms_delivery_failure)
            ResponseType.SMS_RETRIEVAL_ATTEMPTS_EXCEEDED -> context.getString(R.string.dlp_error_sms_retrieval_attempts_exceeded)
            ResponseType.SMS_AUTH_NOT_SUPPORTED_FOR_BUSINESS -> context.getString(R.string.dlp_error_sms_auth_not_supported_for_business)
            ResponseType.PASSWORD_REQUIRED -> context.getString(R.string.dlp_error_password_required)
            ResponseType.PASSWORD_INVALID -> context.getString(R.string.dlp_error_password_invalid)
            ResponseType.PRECONDITION_REQUEST_SPECIFIED -> context.getString(R.string.dlp_error_precondition_request_specified)
            ResponseType.MANUAL_REGISTRATION_ALREADY_SPECIFIED -> context.getString(R.string.dlp_error_manual_registration_already_specified)
            ResponseType.CITY_REQUIRED -> context.getString(R.string.dlp_error_city_required)
            ResponseType.CONSUMER_NOT_FOUND -> context.getString(R.string.dlp_error_consumer_not_found)
            ResponseType.CONSUMER_ACCOUNT_DISABLED -> context.getString(R.string.dlp_error_consumer_account_disabled)
            ResponseType.CONSUMER_CREDENTIAL_INVALID -> context.getString(R.string.dlp_error_consumer_credential_invalid)
            ResponseType.MULTIPLE_CONSUMERS_RETURNED -> context.getString(R.string.dlp_error_multiple_consumers_returned)
            ResponseType.REMOTE_SERVER_UNREACHABLE -> context.getString(R.string.dlp_error_server_unreachable)
            ResponseType.REMOTE_SERVER_SQL_ERROR -> context.getString(R.string.dlp_error_server_database)
            else -> super.getLocalizedMessage(context)
        }
    }
}