package ua.com.wl.dlp.data.api.responses.news

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BaseNewsItemResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("thumb_image") var thumbImage: String? = null,
    @SerializedName("short_description") var shortDescription: String? = null,
    @SerializedName("publication_start_datetime") var publicationStartDate: String? = null,
    @SerializedName("publication_end_date") var publicationEndDate: String? = null)