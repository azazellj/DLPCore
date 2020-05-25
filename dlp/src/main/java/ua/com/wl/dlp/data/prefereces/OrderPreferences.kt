package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences

import ua.com.wl.dlp.data.prefereces.models.OrderPrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class OrderPreferences(
    context: Context,
    private val gson: Gson
) : BasePreferences(context, OrderPreferences::class.java.simpleName) {

    companion object {

        const val KEY_ORDER_PREFS = "order_prefs"
    }

    var orderPrefs: OrderPrefs
        set(value) {
            save(KEY_ORDER_PREFS, gson.toJson(value))
        }
        get() {
            val json = getString(KEY_ORDER_PREFS)
            return if (json.isNonNullOrEmpty()) {
                gson.fromJson(json, OrderPrefs::class.java)
            } else {
                OrderPrefs()
            }
        }

    fun removeOrderPrefs() = remove(KEY_ORDER_PREFS)
}