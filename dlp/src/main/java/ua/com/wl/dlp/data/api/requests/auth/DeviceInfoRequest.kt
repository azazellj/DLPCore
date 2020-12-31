package ua.com.wl.dlp.data.api.requests.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeviceInfoRequest constructor(
    @Json(name = "model")
    val model: String? = "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}",

    @Json(name = "app_version")
    val appVersion: String? = null,

    @Json(name = "operation_system")
    val operationSystem: String? = "ANDROID",

    @Json(name = "os_version")
    val operationSystemVersion: String? = "SDK level: ${android.os.Build.VERSION.SDK_INT} (${android.os.Build.VERSION.RELEASE})"
)