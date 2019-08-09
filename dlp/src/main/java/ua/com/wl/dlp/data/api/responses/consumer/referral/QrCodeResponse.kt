package ua.com.wl.dlp.data.api.responses.consumer.referral

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class QrCodeResponse(@SerializedName("qr_code") val qrCode: String)