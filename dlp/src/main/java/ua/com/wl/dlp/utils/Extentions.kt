package ua.com.wl.dlp.utils

import okhttp3.Request

import ua.com.wl.dlp.data.api.responses.PagedResponse

/**
 * @author Denis Makovskyi
 */

inline fun <T> T.only(block: (T) -> Unit) = block(this)

fun IntArray?.add(element: Int): IntArray {
    val resultArray: IntArray
    if (this == null || isEmpty()) {
        resultArray = IntArray(1) { element }
    } else {
        resultArray = IntArray(size + 1)
        copyInto(resultArray, 0, 0, size)
        resultArray[resultArray.lastIndex] = element
    }
    return resultArray
}

inline fun <reified T> Array<T>?.add(element: T): Array<T?> {
    val resultArray: Array<T?>
    if (this == null) {
        resultArray = Array(1) { element }
    } else {
        resultArray = copyOf(size + 1)
        resultArray[size] = element
    }
    return resultArray
}

fun CharSequence?.isNonNullOrEmpty(): Boolean = this != null && this.isNotEmpty()

fun PagedResponse<*>.previousPage(): Int? =
    previousPage?.let { getQueryParam(it, "page") }?.toInt()

fun PagedResponse<*>.nextPage(): Int? =
    nextPage?.let { getQueryParam(it, "page") }?.toInt()

internal fun String?.toJwt(): String = "JWT $this"

internal fun Request.hasHeader(name: String, value: String): Boolean =
    header(name)?.let { it -> it.isNotEmpty() && it == value } ?: false