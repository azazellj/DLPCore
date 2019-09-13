package ua.com.wl.dlp.domain

import retrofit2.Response

import ua.com.wl.archetype.utils.Optional
import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.domain.exeptions.CoreException
import ua.com.wl.dlp.domain.exeptions.api.ApiException

/**
 * @author Denis Makovskyi
 */

open class UseCase(private val errorsMapper: ErrorsMapper) {

    protected suspend fun <T : Any> callApi(
        call: suspend () -> Response<T>,
        errorClass: Class<out CoreException>? = null

    ): Result<Optional<T>> =
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(Optional.ofNullable(response.body()))

            } else {
                val error = if (response.errorBody() != null && errorClass != null) {
                    errorsMapper.createExceptionFromBody(response.errorBody(), errorClass)
                } else {
                    ApiException()
                }
                Result.Failure(error)
            }

        } catch (e: Exception) {
            Result.Failure(ApiException(cause = e))
        }
}
