package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class ConsumerPreferences constructor(
    context: Context,
    private val gson: Gson
) : BasePreferences(context, ConsumerPreferences::class.java.simpleName) {

    companion object {

        const val KEY_PROFILE_PREFS = "profile_prefs"
    }

    var profilePrefs: ProfilePrefs
        set(value) {
            save(KEY_PROFILE_PREFS, gson.toJson(value))
        }
        get() {
            val json = getString(KEY_PROFILE_PREFS)
            return if (json.isNonNullOrEmpty()) {
                gson.fromJson(json, ProfilePrefs::class.java)
            } else {
                ProfilePrefs()
            }
        }

    fun removeProfilePrefs() = remove(KEY_PROFILE_PREFS)
}