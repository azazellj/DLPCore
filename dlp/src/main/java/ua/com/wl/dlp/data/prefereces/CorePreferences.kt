package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.AuthPrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class CorePreferences(
    context: Context,
    private val gson: Gson
) : BasePreferences(context, CorePreferences::class.java.simpleName) {

    companion object {

        const val KEY_AUTH_PREFS = "auth_prefs"
        const val KEY_SELECTED_APP_INDEX = "selected_app_index"
    }

    var authPrefs: AuthPrefs
        set(value) {
            save(KEY_AUTH_PREFS, gson.toJson(value))
        }
        get() {
            val json = getString(KEY_AUTH_PREFS)
            return if (json.isNonNullOrEmpty()) {
                gson.fromJson(json, AuthPrefs::class.java)
            } else {
                AuthPrefs()
            }
        }

    fun removeAuthPrefs() = remove(KEY_AUTH_PREFS)

    fun hasSelectedAppIdIndex(): Boolean {
        return getSelectedAppIdIndex() != -1
    }

    fun getSelectedAppIdIndex(): Int {
        return getInteger(KEY_SELECTED_APP_INDEX, -1)
    }

    fun setSelectedAppId(appIdIndex: Int) {
        save(KEY_SELECTED_APP_INDEX, appIdIndex)
    }
}