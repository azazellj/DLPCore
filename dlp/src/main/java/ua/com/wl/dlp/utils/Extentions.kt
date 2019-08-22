package ua.com.wl.dlp.utils

import okhttp3.Request

import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.consumer.profile.ProfileResponse
import ua.com.wl.dlp.data.prefereces.models.ProfilePrefs

/**
 * @author Denis Makovskyi
 */

inline fun <T> T.only(block: (T) -> Unit) = block(this)

fun CharSequence?.isNonNullOrEmpty(): Boolean = this != null && this.isNotEmpty()

fun PagedResponse<*>.previousPage(): Int? =
    previousPage?.let { getQueryParam(it, "page") }?.toInt()

fun PagedResponse<*>.nextPage(): Int? =
    nextPage?.let { getQueryParam(it, "page") }?.toInt()

fun ProfileResponse.toPrefs(): ProfilePrefs =
    ProfilePrefs(
        this.firstName, this.patronymic, this.lastName,
        this.gender, this.email, this.phone,
        this.address, this.city, this.birthDate,
        this.isMarried, this.language, this.timezone,
        this.balance, this.inviteCode, this.referralCode, this.comment)

internal fun Request.hasHeader(name: String, value: String): Boolean =
    header(name)?.let { it -> it.isNotEmpty() && it == value } ?: false