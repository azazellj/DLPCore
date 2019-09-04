package ua.com.wl.dlp.domain

/**
 * @author Denis Makovskyi
 */

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: Throwable) : Result<Nothing>()

    fun <R> map(block: (T) -> R): Result<R> = when(this) {
        is Success -> Success(block(data))
        is Failure -> this
    }

    fun <R> flatMap(block: (T) -> Result<R>): Result<R> = when(this) {
        is Success -> block(data)
        is Failure -> this
    }

    fun onEach(block: () -> Unit): Result<T> = apply {
        block()
    }

    fun onSuccess(block: (T) -> Unit): Result<T> = apply {
        if (this is Success) {
            block(data)
        }
    }

    fun onFailure(block: (Throwable) -> Unit): Result<T> = apply {
        if (this is Failure) {
            block(error)
        }
    }

    suspend fun sOnEach(block: suspend () -> Unit): Result<T> = apply {
        block()
    }

    suspend fun sOnSuccess(block: suspend (T) -> Unit): Result<T> = apply {
        if (this is Success) {
            block(data)
        }
    }

    suspend fun sOnFailure(block: suspend (Throwable) -> Unit): Result<T> = apply {
        if (this is Failure) {
            block(error)
        }
    }
}