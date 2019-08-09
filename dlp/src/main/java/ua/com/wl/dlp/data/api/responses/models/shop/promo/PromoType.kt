package ua.com.wl.dlp.data.api.responses.models.shop.promo

import com.google.gson.annotations.SerializedName

enum class PromoType(val type: String) {
    @SerializedName("SALE")
    SALE("SALE"),
    @SerializedName("GIFT")
    GIFT("GIFT"),
    @SerializedName("EVENT")
    EVENT("EVENT"),
    @SerializedName("BONUS")
    BONUS("BONUS")
}