package ua.com.wl.dlp.data.events

import ua.com.wl.archetype.core.android.bus.Bus

object EventsCreator {

    fun balanceInvalidationEvent(balance: Long, isSavedInPreferences: Boolean = true) =
        Bus.eventsBus?.post(BalanceInvalidationEvent(balance, isSavedInPreferences))
}