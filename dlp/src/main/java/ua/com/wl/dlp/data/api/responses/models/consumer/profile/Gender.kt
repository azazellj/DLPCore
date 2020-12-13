package ua.com.wl.dlp.data.api.responses.models.consumer.profile

import com.squareup.moshi.Json

enum class Gender {
    @Json(name = "MALE")
    MALE,

    @Json(name = "FEMALE")
    FEMALE
}