package ua.com.wl.dlp.utils

import okhttp3.Request

import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs

/**
 * @author Denis Makovskyi
 */

fun CharSequence?.isNonNullOrEmpty(): Boolean = this != null && this.isNotEmpty()

fun Request.hasHeader(name: String, value: String): Boolean = header(name)?.let { it ->
    it.isNotEmpty() && it == value
} ?: false

fun ProfileResponse.toPrefs(): ProfilePrefs = ProfilePrefs(
    this.firstName, this.patronymic, this.lastName,
    this.gender, this.email, this.phone,
    this.address, this.city, this.birthDate,
    this.isMarried, this.language, this.timezone,
    this.balance, this.inviteCode, this.referralCode, this.comment)