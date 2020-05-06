package ua.com.wl.dlp.data.api.responses.models.consumer.coupons

import com.google.gson.annotations.SerializedName

import ua.com.wl.dlp.data.api.responses.models.another.NoveltyDatesRange
import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoSettings

/**
 * @author Denis Makovskyi
 */

data class CouponOffer(
    @SerializedName("id")
    val id: Int,

    @SerializedName("trade_item")
    val tradeItem: CouponOfferTradeItem,

    @SerializedName("is_new")
    val isNew: Boolean,

    @SerializedName("is_promo_offer")
    val isPromo: Boolean,

    @SerializedName("in_favorite")
    val isFavourite: Boolean,

    @SerializedName("name")
    val name: String,

    @SerializedName("thumb_image")
    val thumbImage: String?,

    @SerializedName("short_description")
    val shortDescription: String,

    @SerializedName("outcome")
    val outcome: String?,

    @SerializedName("hide_price")
    val isPriceHidden: Boolean,

    @SerializedName("promo_settings")
    val promoSettings: PromoSettings?,

    @SerializedName("novelty_date_range")
    val noveltyDatesRange: NoveltyDatesRange?)