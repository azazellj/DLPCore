package ua.com.wl.dlp.data.api.responses.shop

import com.google.gson.annotations.SerializedName
import com.idanatz.oneadapter.external.interfaces.Diffable

/**
 * @author Denis Makovskyi
 */

class CityShopsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("is_native_city") val isNativeCity: Boolean,
    @SerializedName("shops") val shops: List<ShopResponse>): Diffable {

    override fun getUniqueIdentifier(): Long = id.toLong()

    override fun areContentTheSame(other: Any): Boolean = other is CityShopsResponse && other.id == id
}