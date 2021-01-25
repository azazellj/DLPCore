package ua.com.wl.dlp.data.api.responses.models.social

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SocialNetwork(
    @Json(name = "code")
    val code: SocialNetworkCode
) : Parcelable