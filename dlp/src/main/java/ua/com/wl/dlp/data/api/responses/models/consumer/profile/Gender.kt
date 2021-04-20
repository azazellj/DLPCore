package ua.com.wl.dlp.data.api.responses.models.consumer.profile

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.ToJson


enum class Gender {
    @Json(name = "MALE")
    MALE,

    @Json(name = "FEMALE")
    FEMALE
}

object GenderAdapter {
    @ToJson
    fun toJson(gender: Gender?): String? {
        return gender?.name
    }

    @FromJson
    fun fromJson(gender: String?): Gender? {
        if (gender.isNullOrBlank()) return null

        return Gender.valueOf(gender)
    }
}