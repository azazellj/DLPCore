package ua.com.wl.dlp.data.events

import ua.com.wl.archetype.core.android.bus.Bus

/**
 * @author Denis Makovskyi
 */

object EventsFactory {

    fun balance(balance: Long, isSavedInPreferences: Boolean = true) =
        Bus.send(BalanceEvent(balance, isSavedInPreferences))

    fun session(isValid: Boolean, httpCode: Int) =
        Bus.send(SessionEvent(isValid, httpCode))
}