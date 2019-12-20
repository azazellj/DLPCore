package ua.com.wl.dlp.data.api.responses.models.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class City constructor(
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String)