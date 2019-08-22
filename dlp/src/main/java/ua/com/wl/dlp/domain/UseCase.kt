package ua.com.wl.dlp.domain

import retrofit2.Response

import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.domain.exeptions.CoreException
import ua.com.wl.dlp.domain.exeptions.ApiException

/**
 * @author Denis Makovskyi
 */

open class UseCase(private val errorsMapper: ErrorsMapper) {

    protected suspend fun <T : Any> callApi(
        call: suspend () -> Response<T>, errorClass: Class<out CoreException>? = null): Result<T> =
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body())

            } else {
                Result.Failure(if (response.errorBody() != null && errorClass != null) {
                    errorsMapper.createExceptionFromBody(
                        response.errorBody(), errorClass)

                } else {
                    ApiException()
                })
            }

        } catch (e: Exception) {
            Result.Failure(ApiException(cause = e))
        }
}
