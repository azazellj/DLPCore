package ua.com.wl.dlp.data.api.responses.news

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
open class BaseArticleResponse(
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    var name: String = "",

    @Json(name = "thumb_image")
    var thumbImage: String? = null,

    @Json(name = "short_description")
    var shortDescription: String = "",

    @Json(name = "publication_start_datetime")
    var publicationStartDate: String = "",

    @Json(name = "publication_end_date")
    var publicationEndDate: String = ""
) : Parcelable