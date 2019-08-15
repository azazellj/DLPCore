package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName
import com.idanatz.oneadapter.external.interfaces.Diffable

/**
 * @author Denis Makovskyi
 */

class ShopNewsItemResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("thumb_image") val thumbImage: String,
    @SerializedName("short_description") val description: String,
    @SerializedName("publication_start_datetime") val publicationStartDate: String,
    @SerializedName("publication_end_date") val publicationEndDate: String): Diffable {

    override fun getUniqueIdentifier(): Long = id.toLong()

    override fun areContentTheSame(other: Any): Boolean = other is ShopNewsItemResponse && other.id == id
}