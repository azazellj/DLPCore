package ua.com.wl.dlp.data.api.responses.models.consumer.history.notifications

import com.squareup.moshi.Json

enum class PushType {
    @Json(name = "show_shop")
    SHOP,

    @Json(name = "rank_reached")
    RANK,

    @Json(name = "show_offer")
    OFFER,

    @Json(name = "new_order")
    ORDER,

    @Json(name = "show_coupon")
    COUPON,

    @Json(name = "show_news_item")
    ARTICLE,

    @Json(name = "show_promotion")
    PROMOTION,

    @Json(name = "show_pre_order")
    PRE_ORDER,

    @Json(name = "shop_reward")
    SHOP_REWARD,

    @Json(name = "bonuses_reward")
    BONUSES_REWARD,

    @Json(name = "show_qr_code")
    BIRTHDAY_REWARD,

    @Json(name = "cash_back")
    CASH_BACK_ACCRUED
}