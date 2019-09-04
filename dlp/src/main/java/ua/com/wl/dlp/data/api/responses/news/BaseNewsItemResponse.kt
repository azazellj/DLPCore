package ua.com.wl.dlp.data.api.responses.news

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

open class BaseNewsItemResponse(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("thumb_image") var thumbImage: String = "",
    @SerializedName("short_description") var description: String = "",
    @SerializedName("publication_start_datetime") var publicationStartDate: String = "",
    @SerializedName("publication_end_date") var publicationEndDate: String = "")