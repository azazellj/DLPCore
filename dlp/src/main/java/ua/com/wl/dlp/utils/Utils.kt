package ua.com.wl.dlp.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoType
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity

/**
 * @author Denis Makovskyi
 */

fun getQueryParam(url: String, key: String): String? {
    if (url.contains("?")) {
        val params = url.substring(url.indexOf("?") + 1).split("&")
        for (param in params) {
            val keyVal = param.split("=")
            if (keyVal[0] == key) return keyVal[1]
        }
    }
    return null
}

fun calculatePersistedOffersCount(offers: List<OfferEntity>): Int {
    return offers.sumBy { offer -> offer.preOrdersCount }
}

fun calculatePersistedOffersPrice(offers: List<OfferEntity>): Double {
    return offers.sumByDouble { offer ->
        val price = if (offer.isPromoOffer) {
            when(offer.promoSettings?.promoType) {
                PromoType.SALE -> {
                    offer.promoSettings
                        ?.promoParams
                        ?.salePrice
                }
                PromoType.EVENT -> {
                    offer.promoSettings
                        ?.promoParams
                        ?.eventPrice
                }
                PromoType.DISCOUNT -> {
                    offer.promoSettings
                        ?.promoParams
                        ?.discountPrice
                }
                else -> offer.priceInCurrency
            }

        } else {
            offer.priceInCurrency
        }
        price mulAsDouble offer.preOrdersCount
    }
}

internal fun sendBroadcastMessage(context: Context, action: String, extras: Bundle? = null) {
    val intent = Intent().apply {
        setAction(action)
        extras?.only { putExtras(it) }
    }
    context.sendBroadcast(intent)
}