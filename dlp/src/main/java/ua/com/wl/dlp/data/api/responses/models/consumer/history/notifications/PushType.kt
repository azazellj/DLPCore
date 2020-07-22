package ua.com.wl.dlp.data.api.responses.models.consumer.history.notifications

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class PushType(val value: String) {
    @SerializedName("show_shop")
    SHOP("show_shop"),
    @SerializedName("rank_reached")
    RANK("rank_reached"),
    @SerializedName("show_offer")
    OFFER("show_offer"),
    @SerializedName("new_order")
    ORDER("new_order"),
    @SerializedName("show_coupon")
    COUPON("show_coupon"),
    @SerializedName("show_news_item")
    ARTICLE("show_news_item"),
    @SerializedName("show_promotion")
    PROMOTION("show_promotion"),
    @SerializedName("show_pre_order")
    PRE_ORDER("show_pre_order"),
    @SerializedName("shop_reward")
    SHOP_REWARD("shop_reward"),
    @SerializedName("bonuses_reward")
    BONUSES_REWARD("bonuses_reward"),
    @SerializedName("show_qr_code")
    BIRTHDAY_REWARD("show_qr_code"),
    @SerializedName("cash_back")
    CASH_BACK_ACCRUED("cash_back")
}