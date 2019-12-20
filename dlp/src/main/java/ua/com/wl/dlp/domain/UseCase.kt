package ua.com.wl.dlp.domain

import kotlin.reflect.KClass

import retrofit2.Response

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.domain.exeptions.db.DatabaseException

/**
 * @author Denis Makovskyi
 */

open class UseCase constructor(private val errorsMapper: ErrorsMapper) {

    protected suspend fun <T : Any> callApi(
        call: suspend () -> Response<T>,
        errorClass: KClass<out ApiException>? = null
    ): Result<Optional<T>> =
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(Optional.ofNullable(response.body()))

            } else {
                val errorBody = response.errorBody()
                val throwable = if (errorBody != null && errorClass != null) {
                    errorsMapper.createExceptionFromResponseBody(errorBody, errorClass)
                } else {
                    ApiException()
                }
                Result.Failure(throwable)
            }

        } catch (e: Exception) {
            Result.Failure(ApiException(cause = e))
        }

    protected suspend fun <T : Any> callQuery(call: suspend () -> T): Result<T> =
        try {
            Result.Success(call.invoke())

        } catch (e: Exception) {
            Result.Failure(DatabaseException(cause = e))
        }
}
