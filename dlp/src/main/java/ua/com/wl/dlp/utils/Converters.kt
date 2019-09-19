package ua.com.wl.dlp.utils

import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.api.responses.shop.offer.BaseOfferResponse
import ua.com.wl.dlp.data.db.entities.shops.OfferEntity
import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoParams
import ua.com.wl.dlp.data.db.entities.shops.embedded.offer.OfferEntityPromoSettings
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs

/**
 * @author Denis Makovskyi
 */

fun ProfileResponse.toPrefs(): ProfilePrefs =
    ProfilePrefs(
        this.firstName, this.patronymic, this.lastName,
        this.gender, this.email, this.phone,
        this.address, this.city, this.birthDate,
        this.isMarried, this.language, this.timezone,
        this.balance, this.inviteCode, this.referralCode, this.comment)

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
        id, shopId, tradeItem,
        name, thumbImage, shortDescription,
        priceInBonuses, priceInCurrency, cashBackPercentage,
        isPromo, isFavourite, count, entityPromoSettings)
}