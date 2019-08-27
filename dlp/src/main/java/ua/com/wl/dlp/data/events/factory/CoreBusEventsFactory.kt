package ua.com.wl.dlp.data.events.factory

import ua.com.wl.archetype.core.android.bus.Bus
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.events.prefs.ProfileBusEvent
import ua.com.wl.dlp.data.events.session.SessionBusEvent
import ua.com.wl.dlp.data.events.shop.offer.OfferBusEvent
import ua.com.wl.dlp.utils.only

/**
 * @author Denis Makovskyi
 */

object CoreBusEventsFactory {

    fun firstName(name: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.FIRST_NAME,
            ProfileBusEvent.FieldValue.StringValue(name)).only { Bus.send(it) }

    fun patronymic(patronymic: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.PATRONYMIC,
            ProfileBusEvent.FieldValue.StringValue(patronymic)).only { Bus.send(it) }

    fun lastName(name: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.LAST_NAME,
            ProfileBusEvent.FieldValue.StringValue(name)).only { Bus.send(it) }

    fun city(city: City?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.CITY,
            ProfileBusEvent.FieldValue.CityObjectValue(city)).only { Bus.send(it) }

    fun phone(phone: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.PHONE,
            ProfileBusEvent.FieldValue.StringValue(phone)).only { Bus.send(it) }

    fun email(email: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.EMAIL,
            ProfileBusEvent.FieldValue.StringValue(email)).only { Bus.send(it) }

    fun balance(balance: Long?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.BALANCE,
            ProfileBusEvent.FieldValue.LongValue(balance)).only { Bus.send(it) }

    fun qrCode(code: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.QR_CODE,
            ProfileBusEvent.FieldValue.StringValue(code)).only { Bus.send(it) }

    fun inviteCode(code: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.INVITE_CODE,
            ProfileBusEvent.FieldValue.StringValue(code)).only { Bus.send(it) }

    fun referralCode(code: String?, saved: Boolean = true) =
        ProfileBusEvent(
            saved,
            ProfileBusEvent.Field.REFERRAL_CODE,
            ProfileBusEvent.FieldValue.StringValue(code)).only { Bus.send(it) }

    fun offerFavouriteStatus(offerId: Int, tradeId: Int? = null, isFavourite: Boolean?) =
        OfferBusEvent(
            offerId, tradeId,
            OfferBusEvent.Field.IS_FAVOURITE,
            OfferBusEvent.FieldValue.BooleanValue(isFavourite)).only { Bus.send(it) }

    fun session(isValid: Boolean, httpCode: Int) =
        SessionBusEvent(isValid, httpCode).only { Bus.send(it) }
}