package ua.com.wl.notifications.ext

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.*

fun Context.findAppLauncherClass(): Class<*>? {
    return packageManager
        .getLaunchIntentForPackage(applicationContext.packageName)
        ?.component
        ?.className
        ?.let {
            Class.forName(it)
        }
}

fun Context.stringByName(resName: String?): String? {
    if (resName == null) return null
    val resNameNew = resName.replace("\"", "")
    val resId = resources.getIdentifier(resNameNew, "string", packageName)
    return if (resId == 0) null else getString(resId)
}

fun Context.stringByName(resName: String?, args: Array<out Any>): String? {
    val string = stringByName(resName)
    return if (string == null) null else String.format(Locale.getDefault(), string, *args)
}

fun Context.sendLocalBroadcastMessage(action: String, extras: Bundle? = null) {
    val intent = Intent().apply {
        setAction(action)
        extras?.let { putExtras(it) }
    }
    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
}

fun Context.iconFromMetaData(): Int {
    val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    val iconRes = info.metaData.getInt("com.google.firebase.messaging.default_notification_icon")
    val appIcon = info.icon
    return if (iconRes != 0) iconRes else appIcon
}

@ColorRes
fun Context.colorFromMetaData(): Int {
    val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    return info.metaData.getInt("com.google.firebase.messaging.default_notification_color")
}