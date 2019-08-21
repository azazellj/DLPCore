package ua.com.wl.dlp.data.events.factory

import ua.com.wl.archetype.core.android.bus.Bus
import ua.com.wl.dlp.data.api.responses.models.auth.City
import ua.com.wl.dlp.data.events.prefs.ConsumerPrefsEvent
import ua.com.wl.dlp.data.events.session.SessionEvent
import ua.com.wl.dlp.utils.only

/**
 * @author Denis Makovskyi
 */

object CoreEventsFactory {

    fun firstName(name: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.FIRST_NAME,
            ConsumerPrefsEvent.FieldValue.StringValue(name)).only { Bus.send(it) }

    fun patronymic(patronymic: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.PATRONYMIC,
            ConsumerPrefsEvent.FieldValue.StringValue(patronymic)).only { Bus.send(it) }

    fun lastName(name: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.LAST_NAME,
            ConsumerPrefsEvent.FieldValue.StringValue(name)).only { Bus.send(it) }

    fun city(city: City?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.CITY,
            ConsumerPrefsEvent.FieldValue.CityObjectValue(city)).only { Bus.send(it) }

    fun phone(phone: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.PHONE,
            ConsumerPrefsEvent.FieldValue.StringValue(phone)).only { Bus.send(it) }

    fun email(email: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.EMAIL,
            ConsumerPrefsEvent.FieldValue.StringValue(email)).only { Bus.send(it) }

    fun balance(balance: Long?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.BALANCE,
            ConsumerPrefsEvent.FieldValue.LongValue(balance)).only { Bus.send(it) }

    fun qrCode(code: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.QR_CODE,
            ConsumerPrefsEvent.FieldValue.StringValue(code)).only { Bus.send(it) }

    fun inviteCode(code: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.INVITE_CODE,
            ConsumerPrefsEvent.FieldValue.StringValue(code)).only { Bus.send(it) }

    fun referralCode(code: String?, saved: Boolean = true) =
        ConsumerPrefsEvent(
            saved,
            ConsumerPrefsEvent.Field.REFERRAL_CODE,
            ConsumerPrefsEvent.FieldValue.StringValue(code)).only { Bus.send(it) }

    fun session(isValid: Boolean, httpCode: Int) =
        SessionEvent(isValid, httpCode).only { Bus.send(it) }
}