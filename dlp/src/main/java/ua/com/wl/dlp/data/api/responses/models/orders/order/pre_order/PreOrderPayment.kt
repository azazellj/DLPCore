package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreOrderPayment(
    @Json(name = "amount")
    val amount: String,

    @Json(name = "currency")
    val currency: String,

    @Json(name = "authorizationType")
    val authType: String,

    @Json(name = "orderReference")
    val orderRef: String,

    @Json(name = "orderDate")
    val orderDate: String,

    @Json(name = "orderTimeout")
    val orderTimeout: Long,

    @Json(name = "serviceUrl")
    val serviceUrl: String,

    @Json(name = "merchantAccount")
    val merchantAccount: String,

    @Json(name = "merchantSignature")
    val merchantSignature: String,

    @Json(name = "merchantDomainName")
    val merchantDomainName: String,

    @Json(name = "merchantTransactionSecureType")
    val merchantTransactionSecureType: String,

    @Json(name = "productName")
    val productName: List<String>,

    @Json(name = "productCount")
    val productCount: List<String>,

    @Json(name = "productPrice")
    val productPrice: List<String>
)