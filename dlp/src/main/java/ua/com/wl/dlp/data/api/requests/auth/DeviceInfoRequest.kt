package ua.com.wl.dlp.data.api.requests.auth

import com.google.gson.annotations.SerializedName

/**
 * @author Denis Makovskyi
 */

data class DeviceInfoRequest constructor(
    @SerializedName("mobile_phone")
    val model: String? = "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}",

    @SerializedName("app_version")
    val appVersion: String? = null,

    @SerializedName("operation_system")
    val operationSystem: String? = "ANDROID",

    @SerializedName("os_version")
    val operationSystemVersion: String? = "SDK level: ${android.os.Build.VERSION.SDK_INT} (${android.os.Build.VERSION.RELEASE})")