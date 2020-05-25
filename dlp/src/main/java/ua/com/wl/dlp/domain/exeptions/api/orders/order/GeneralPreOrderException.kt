package ua.com.wl.dlp.domain.exeptions.api.orders.order

import android.content.Context

import ua.com.wl.dlp.R
import ua.com.wl.dlp.data.api.responses.ResponseType
import ua.com.wl.dlp.domain.exeptions.api.ApiException

class GeneralPreOrderException(type: String, cause: Throwable): ApiException(type, cause) {

    override fun getLocalizedMessage(context: Context): String {
        return when (message) {
            ResponseType.SHOP_REQUIRED -> context.getString(R.string.dlp_error_shop_required)
            ResponseType.PRE_ORDER_INVALID_TRADE_ITEMS -> context.getString(R.string.dlp_error_pre_order_invalid_trade_items)
            ResponseType.PRE_ORDER_INVALID_DELIVERY_TYPE -> context.getString(R.string.dlp_error_pre_order_invalid_delivery_type)
            ResponseType.PRE_ORDER_ILLEGAL_FLAG_USE_BONUSES -> context.getString(R.string.dlp_error_pre_order_illegal_flag_use_bonuses)
            ResponseType.PRE_ORDER_INCORRECT_READINESS_TIME -> context.getString(R.string.dlp_error_pre_order_incorrect_readiness_time)
            else -> super.getLocalizedMessage(context)
        }
    }
}