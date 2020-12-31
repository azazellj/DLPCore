package ua.com.wl.notifications.utils

import com.google.gson.Gson

fun jsonToStringArray(json: String?, gson: Gson): Array<String> {
    return gson.fromJson(json, Array<String>::class.java)
}
