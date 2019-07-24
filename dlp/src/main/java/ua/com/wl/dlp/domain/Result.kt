package ua.com.wl.dlp.domain

/**
 * @author Denis Makovskyi
 */

sealed class Result<out T> {

    data class Success<out T>(val data: T? = null) : Result<T>()
    data class Failure(val error: Throwable? = null) : Result<Nothing>()

    fun <R> fMap(block: (T?) -> R?): Result<R> = when (this) {
        is Success -> Success(block(data))
        is Failure -> Failure(error)
    }

    fun nothing(): Result<Nothing> = when (this) {
        is Success -> Success()
        is Failure -> Failure(error)
    }
}