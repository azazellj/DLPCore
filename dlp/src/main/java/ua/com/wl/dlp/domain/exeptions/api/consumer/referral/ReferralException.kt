package ua.com.wl.dlp.domain.exeptions.api.consumer.referral

import android.content.Context

import ua.com.wl.dlp.R
import ua.com.wl.dlp.data.api.responses.ResponseType
import ua.com.wl.dlp.domain.exeptions.api.ApiException

class ReferralException(type: String?, cause: Throwable?) : ApiException(type, cause) {
    override fun getLocalizedMessage(context: Context): String {
        return when (message) {
            ResponseType.INVITE_CODE_REQUIRED -> context.getString(R.string.dlp_error_invite_code_required)
            ResponseType.INVITE_CODE_NOT_FOUND -> context.getString(R.string.dlp_error_invite_code_not_found)
            ResponseType.CONSUMER_ALREADY_INVITED -> context.getString(R.string.dlp_error_consumer_already_invited)
            ResponseType.CONSUMER_SELF_INVITE -> context.getString(R.string.dlp_error_consumer_self_invited)
            ResponseType.CONSUMER_CAN_NOT_BE_INVITED -> context.getString(R.string.dlp_error_consumer_can_not_be_invited)
            else -> super.getLocalizedMessage(context)
        }
    }
}