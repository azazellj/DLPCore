package ua.com.wl.dlp.data.api.responses.models.shop

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class ShopNewsItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("thumb_image") val thumbImage: String,
    @SerializedName("short_description") val description: String,
    @SerializedName("publication_start_datetime") val publicationStartDate: String,
    @SerializedName("publication_end_date") val publicationEndDate: String)