package ua.com.wl.dlp.data.api.responses.models.orders.order.pre_order

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class PreOrderPayment(
    @SerializedName("amount")
    val amount: String,

    @SerializedName("currency")
    val currency: String,

    @SerializedName("authorizationType")
    val authType: String,

    @SerializedName("orderReference")
    val orderRef: String,

    @SerializedName("orderDate")
    val orderDate: String,

    @SerializedName("orderTimeout")
    val orderTimeout: Long,

    @SerializedName("serviceUrl")
    val serviceUrl: String,

    @SerializedName("merchantAccount")
    val merchantAccount: String,

    @SerializedName("merchantSignature")
    val merchantSignature: String,

    @SerializedName("merchantDomainName")
    val merchantDomainName: String,

    @SerializedName("merchantTransactionSecureType")
    val merchantTransactionSecureType: String,

    @SerializedName("productName")
    val productName: List<String>,

    @SerializedName("productCount")
    val productCount: List<String>,

    @SerializedName("productPrice")
    val productPrice: List<String>)