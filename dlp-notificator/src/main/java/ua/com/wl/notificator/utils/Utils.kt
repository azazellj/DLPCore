package ua.com.wl.notificator.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.annotation.DrawableRes
import kotlin.reflect.KClass

/**
 * @author Denis Makovskyi
 */

fun bitmapFromResources(context: Context, @DrawableRes id: Int): Bitmap? {
    return BitmapFactory.decodeResource(context.resources, id)
}

fun defaultNotificationSound(): Uri {
    return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
}

fun isApiLevel(level: Int): Boolean {
    return Build.VERSION.SDK_INT >= level
}

fun buildMessage(cls: KClass<*>, message: String): String {
    return "${cls.java.name}: $message"
}