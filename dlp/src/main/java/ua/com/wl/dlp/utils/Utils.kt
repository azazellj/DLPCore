package ua.com.wl.dlp.utils

import android.content.Context

import okhttp3.Request

import ua.com.wl.dlp.R
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs
import ua.com.wl.dlp.domain.exeptions.ApiException

/**
 * @author Denis Makovskyi
 */

fun localizeError(context: Context, error: Throwable?): String =
    when (error) {
        is ApiException -> error.getLocalizedMessage(context)
        else -> context.getString(R.string.dlp_error_api_runtime)
    }

fun Request.hasHeader(name: String, value: String): Boolean = header(name)?.let { it ->
    it.isNotEmpty() && it == value
} ?: false

fun ProfileResponse.toPrefs(): ProfilePrefs = ProfilePrefs(
    this.firstName, this.patronymic, this.lastName,
    this.gender, this.email, this.phone,
    this.address, this.city, this.birthDate,
    this.isMarried, this.language, this.timezone,
    this.balance, this.inviteCode, this.referralCode, this.comment)