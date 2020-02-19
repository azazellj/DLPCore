package ua.com.wl.dlp.data.prefereces

import android.content.Context

import com.google.gson.Gson

import ua.com.wl.archetype.core.android.preferences.BasePreferences
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.data.prefereces.models.RanksPrefs
import ua.com.wl.dlp.utils.isNonNullOrEmpty

/**
 * @author Denis Makovskyi
 */

class ConsumerPreferences(
    context: Context,
    private val gson: Gson
) : BasePreferences(context, ConsumerPreferences::class.java.simpleName) {

    companion object {

        const val KEY_RANK_PREFS = "rank_prefs"
        const val KEY_PROFILE_PREFS = "profile_prefs"
    }

    var rankPrefs: RanksPrefs
        set(value) {
            save(KEY_RANK_PREFS, gson.toJson(value))
        }
        get() {
            val json = getString(KEY_RANK_PREFS)
            return if (json.isNonNullOrEmpty()) {
                gson.fromJson(json, RanksPrefs::class.java)
            } else {
                RanksPrefs()
            }
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

    fun removeRankPrefs() = remove(KEY_RANK_PREFS)

    fun removeProfilePrefs() = remove(KEY_PROFILE_PREFS)
}