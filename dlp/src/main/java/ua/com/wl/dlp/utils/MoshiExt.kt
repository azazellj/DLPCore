package ua.com.wl.dlp.utils

import com.squareup.moshi.Moshi

inline fun <reified T> Moshi.fromJson(field: String?): T? {
    if (field == null) return null
    return adapter(T::class.java).fromJson(field)
}

inline fun <reified T> Moshi.toJson(field: T?): String? {
    if (field == null) return null
    return adapter(T::class.java).toJson(field)
}