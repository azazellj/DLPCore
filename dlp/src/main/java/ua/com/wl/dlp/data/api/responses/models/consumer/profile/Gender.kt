package ua.com.wl.dlp.data.api.responses.models.consumer.profile

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

enum class Gender(val value: String) {
    @SerializedName("MALE")
    MALE("MALE"),
    @SerializedName("FEMALE")
    FEMALE("FEMALE")
}