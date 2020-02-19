package ua.com.wl.dlp.data.prefereces.models

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class RanksPrefs(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("hint")
    val hint: String? = null,

    @SerializedName("icon_url")
    val iconUrl: String? = null,

    @SerializedName("color_hex")
    val colorHex: String? = null)