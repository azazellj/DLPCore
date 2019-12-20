package ua.com.wl.dlp.data.api.responses.shop.rubric

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class RubricResponse constructor(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("thumb_image")
    val thumbImage: String?)