package ua.com.wl.dlp.data.events

import ua.com.wl.archetype.core.android.bus.Bus

/**
 * @author Denis Makovskyi
 */

object CoreEventsFactory {

    fun name(name: String?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = name,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.NAME).let { Bus.send(it) }

    fun city(id: Int?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = id,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.CITY).let { Bus.send(it) }

    fun phone(phone: String?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = phone,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.PHONE).let { Bus.send(it) }

    fun email(email: String?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = email,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.EMAIL).let { Bus.send(it) }

    fun balance(balance: Long?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = balance,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.BALANCE).let { Bus.send(it) }

    fun qrCode(code: String?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = code,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.QR_CODE).let { Bus.send(it) }

    fun inviteCode(code: String?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = code,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.INVITE_CODE).let { Bus.send(it) }

    fun referralCode(code: String?, saved: Boolean = true) =
        PrefsEvent(
            saved = saved,
            value = code,
            storage = PrefsEvent.Storage.CONSUMER,
            destination = PrefsEvent.Destination.PROFILE,
            changedField = PrefsEvent.ChangedField.REFERRAL_CODE).let { Bus.send(it) }

    fun session(isValid: Boolean, httpCode: Int) =
        Bus.send(SessionEvent(isValid, httpCode))
}