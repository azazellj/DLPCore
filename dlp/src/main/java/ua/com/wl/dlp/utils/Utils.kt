package ua.com.wl.dlp.utils

import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineDispatcher

import android.os.Bundle
import android.content.Intent
import android.content.Context

import ua.com.wl.dlp.data.api.responses.models.shop.offer.promo.PromoType
import ua.com.wl.dlp.data.api.responses.models.consumer.history.transactions.BalanceChange
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.prefereces.ConsumerPreferences

/**
 * @author Denis Makovskyi
 */

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

suspend fun processBalanceChanges(
    ioDispatcher: CoroutineDispatcher,
    balanceChange: BalanceChange,
    consumerPreferences: ConsumerPreferences
): List<ProfileBusEvent.Change> {
    return mutableListOf<ProfileBusEvent.Change>().also { changes ->
        val profile = withContext(ioDispatcher) { consumerPreferences.profilePrefs }
        if (profile.balance != balanceChange.resultingBalance) {
            withContext(ioDispatcher) {
                consumerPreferences.profilePrefs = consumerPreferences
                    .profilePrefs
                    .copy(balance = balanceChange.resultingBalance)
            }
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.BALANCE,
                ProfileBusEvent.FieldValue.LongValue(consumerPreferences.profilePrefs.balance)
            ).let { changes.add(it) }
        }
        if (profile.moneyAmount != balanceChange.resultingMoneyAmount) {
            withContext(ioDispatcher) {
                consumerPreferences.profilePrefs = consumerPreferences
                    .profilePrefs
                    .copy(moneyAmount = balanceChange.resultingMoneyAmount)
            }
            ProfileBusEvent.Change(
                true, ProfileBusEvent.Field.MONEY_AMOUNT,
                ProfileBusEvent.FieldValue.StringValue(consumerPreferences.profilePrefs.moneyAmount)
            ).let { changes.add(it) }
        }
    }
}

internal fun sendBroadcastMessage(context: Context, action: String, extras: Bundle? = null) {
    val intent = Intent().apply {
        setAction(action)
        extras?.let { putExtras(it) }
    }
    context.sendBroadcast(intent)
}