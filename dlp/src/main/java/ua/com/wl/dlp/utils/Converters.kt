package ua.com.wl.dlp.utils

import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.data.prefereces.models.BusinessPrefs
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoParams
import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoSettings
import ua.com.wl.dlp.data.api.responses.shop.offer.OfferResponse
import ua.com.wl.dlp.data.api.responses.consumer.info.BusinessResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPermissions

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

fun OfferResponse.toOfferEntity(shopId: Int, count: Int = 0): OfferEntity {
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
    val permissions = OfferEntityPermissions(
        isAvailableForPreOrder = permissions?.isAvailableForPreOrder,
        isAvailableForDelivery = permissions?.isAvailableForDelivery,
    )
    return OfferEntity(
        id = id,
        tradeItem = tradeItem,
        name = name,
        thumbImage = thumbImage,
        shortDescription = shortDescription,
        outcome = outcome,
        priceInBonuses = priceInBonuses,
        priceInCurrency = priceInCurrency,
        cashbackPercentage = cashBackPercentage,
        isPromoOffer = isPromo ?: false,
        isFavouriteOffer = isFavourite,
        promoSettings = entityPromoSettings,
        permissions = permissions,
        isAvailableForPreOrder = isAvailableForPreOrder,
        isAvailableForDelivery = isAvailableForDelivery
    ).apply {
        this.shopId = shopId
        this.preOrdersCount = count
    }
}
