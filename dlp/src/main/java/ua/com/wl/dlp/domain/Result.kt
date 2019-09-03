package ua.com.wl.dlp.domain

/**
 * @author Denis Makovskyi
 */

sealed class Result<out T> {

    data class Success<out T>(val data: T? = null) : Result<T>()
    data class Failure(val error: Throwable? = null) : Result<Nothing>()

    fun <R> fMap(block: (T?) -> R?): Result<R> = when(this) {
        is Success -> Success(block(data))
        is Failure -> this
    }

    suspend fun <R> sfMap(block: suspend (T?) -> R?): Result<R> = when(this) {
        is Success -> Success(block(data))
        is Failure -> this
    }

    fun onSuccess(block: (T?) -> Unit): Result<T> = apply {
        if (this is Success) {
            block(data)
        }
    }

    suspend fun sOnSuccess(block: suspend (T?) -> Unit): Result<T> = apply {
        if (this is Success) {
            block(data)
        }
    }

    fun onFailure(block: (Throwable?) -> Unit): Result<T> = apply {
        if (this is Failure) {
            block(error)
        }
    }

    suspend fun sOnFailure(block: suspend (Throwable?) -> Unit): Result<T> = apply {
        if (this is Failure) {
            block(error)
        }
    }

    fun onEach(block: () -> Unit): Result<T> = apply { block() }

    suspend fun sOnEach(block: suspend () -> Unit): Result<T> = apply { block() }

    fun nothing(): Result<Nothing> = when(this) {
        is Success -> Success()
        is Failure -> this
    }
}