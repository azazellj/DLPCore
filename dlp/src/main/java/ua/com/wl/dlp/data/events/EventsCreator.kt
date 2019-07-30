package ua.com.wl.dlp.data.events

import ua.com.wl.archetype.core.android.bus.Bus

/**
 * @author Denis Makovskyi
 */

object EventsCreator {

    fun balanceInvalidationEvent(balance: Long, isSavedInPreferences: Boolean = true) =
        Bus.eventsBus?.post(BalanceEvent(balance, isSavedInPreferences))
}