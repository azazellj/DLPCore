package ua.com.wl.dlp.utils

import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.data.prefereces.models.BusinessPrefs
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoParams
import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoSettings
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.api.responses.consumer.info.BusinessResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse

/**
 * @author Denis Makovskyi
 */

fun ProfileResponse.toPrefs(): ProfilePrefs {
    return ProfilePrefs(
        firstName, patronymic, lastName,
        gender, birthDate, isMarried,
        city, address, email,
        phone, language, timezone,
        balance, moneyAmount, null,
        inviteCode, referralCode, comment)
}

fun BusinessResponse.toPrefs(): BusinessPrefs {
    return BusinessPrefs(
        id, phone,
        email, address, homePage,
        feedbackEmail, applicationLink)
}

fun BaseOfferResponse.toOfferEntity(shopId: Int, count: Int = 0): OfferEntity {
    val entityPromoParams = OfferEntityPromoParams(
        promoSettings?.promoParams?.giftName,
        promoSettings?.promoParams?.salePrice,
        promoSettings?.promoParams?.eventPrice,
        promoSettings?.promoParams?.eventPlace,
        promoSettings?.promoParams?.discountSize,
        promoSettings?.promoParams?.discountPrice,
        promoSettings?.promoParams?.priceInBonuses,
        promoSettings?.promoParams?.cashBackSize)
    val entityPromoSettings = OfferEntityPromoSettings(
        promoSettings?.promoType,
        promoSettings?.activeSince,
        promoSettings?.activeUntil,
        promoSettings?.activeFrom,
        promoSettings?.activeTo,
        entityPromoParams)
    return OfferEntity(
        id, tradeItem,
        name, thumbImage, shortDescription,
        outcome, priceInBonuses, priceInCurrency,
        cashBackPercentage, isPromo, isFavourite, entityPromoSettings
    ).apply {
        this.shopId = shopId
        this.preOrdersCount = count
    }
}
