package ua.com.wl.dlp.data.prefereces

import android.content.Context
import com.squareup.moshi.Moshi
import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.OrderPrefs
import ua.com.wl.dlp.utils.fromJson
import ua.com.wl.dlp.utils.isNonNullOrEmpty
import ua.com.wl.dlp.utils.toJson

/**
 * @author Denis Makovskyi
 */

class OrderPreferences(
    context: Context,
    private val moshi: Moshi
) : BasePreferences(context, OrderPreferences::class.java.simpleName) {

    companion object {

        const val KEY_ORDER_PREFS = "order_prefs"
    }

    var orderPrefs: OrderPrefs
        set(value) {
            save(KEY_ORDER_PREFS, moshi.toJson(value))
        }
        get() {
            val json = getString(KEY_ORDER_PREFS)
            return if (json.isNonNullOrEmpty()) {
                moshi.fromJson<OrderPrefs>(json) ?: OrderPrefs()
            } else {
                OrderPrefs()
            }
        }

    fun removeOrderPrefs() = remove(KEY_ORDER_PREFS)
}