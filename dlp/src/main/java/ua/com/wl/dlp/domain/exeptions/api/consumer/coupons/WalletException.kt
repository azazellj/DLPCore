package ua.com.wl.dlp.domain.exeptions.api.consumer.coupons

import android.content.Context

import ua.com.wl.dlp.common.R
import ua.com.wl.dlp.data.api.responses.ResponseType
import ua.com.wl.dlp.domain.exeptions.api.ApiException

class WalletException(type: String?, cause: Throwable?) : ApiException(type, cause) {
    override fun getLocalizedMessage(context: Context): String {
        return when (message) {
            ResponseType.COUPON_WALLET_INVALID_URL -> context.getString(R.string.dlp_error_coupon_wallet_invalid_url)
            ResponseType.COUPON_WALLET_UNSUPPORTED_CONFIG -> context.getString(R.string.dlp_error_coupon_wallet_unsupported_config)
            else -> super.getLocalizedMessage(context)
        }
    }
}