package ua.com.wl.dlp.domain

/**
 * @author Denis Makovskyi
 */

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()

    data class Failure(val error: Throwable) : Result<Nothing>()

    fun isSuccess(): Boolean = this is Success

    fun <R> map(mapper: (T) -> R): Result<R> = when(this) {
        is Success -> Success(mapper(data))
        is Failure -> this
    }

    fun <R> flatMap(mapper: (T) -> Result<R>): Result<R> = when(this) {
        is Success -> mapper(data)
        is Failure -> this
    }

    fun <S, R> zipWith(
        second: Result<S>,
        zipper: (T, S) -> R
    ): Result<*> = when(this) {
        is Success -> {
            if (second is Success) {
                Success(zipper(this.data, second.data))
            } else {
                this
            }
        }
        is Failure -> this
    }

    fun onEach(callback: (Boolean) -> Unit): Result<T> = apply {
        callback(this is Success)
    }

    fun onSuccess(callback: (T) -> Unit): Result<T> = apply {
        if (this is Success) {
            callback(data)
        }
    }

    fun onFailure(callback: (Throwable) -> Unit): Result<T> = apply {
        if (this is Failure) {
            callback(error)
        }
    }

    suspend fun <R> sMap(mapper: suspend (T) -> R): Result<R> = when(this) {
        is Success -> Success(mapper(data))
        is Failure -> this
    }

    suspend fun <R> sFlatMap(mapper: suspend (T) -> Result<R>): Result<R> = when(this) {
        is Success -> mapper(data)
        is Failure -> this
    }

    suspend fun <S, R> sZipWith(
        second: Result<S>,
        zipper: suspend (T, S) -> R
    ): Result<*> = when(this) {
        is Success -> {
            if (second is Success) {
                Success(zipper(this.data, second.data))
            } else {
                this
            }
        }
        is Failure -> this
    }

    suspend fun sOnEach(callback: suspend (Boolean) -> Unit): Result<T> = apply {
        callback(this is Success)
    }

    suspend fun sOnSuccess(callback: suspend (T) -> Unit): Result<T> = apply {
        if (this is Success) {
            callback(data)
        }
    }

    suspend fun sOnFailure(callback: suspend (Throwable) -> Unit): Result<T> = apply {
        if (this is Failure) {
            callback(error)
        }
    }
}