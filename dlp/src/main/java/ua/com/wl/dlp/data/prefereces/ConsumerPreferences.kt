package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class ConsumerPreferences(
    context: Context,
    private val gson: Gson) : BasePreferences(context, ConsumerPreferences::class.java.simpleName) {

    companion object {

        const val KEY_PROFILE_PREFS = "profile_prefs"
    }

    var profilePrefs: ProfilePrefs = ProfilePrefs()
        set(value) {
            field = value
            save(KEY_PROFILE_PREFS, gson.toJson(field))
        }
        get() {
            val raw = getString(KEY_PROFILE_PREFS)
            return if (raw.isNonNullOrEmpty()) {
                gson.fromJson(raw, ProfilePrefs::class.java)
                field

            } else {
                field = ProfilePrefs()
                field
            }
        }

    fun removeProfilePrefs() = remove(KEY_PROFILE_PREFS)
}