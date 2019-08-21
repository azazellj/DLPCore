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
        call: suspend () -> Response<T>,
        errorClass: Class<out CoreException>? = null

    ): Result<T> {
        val response = try {
            call.invoke()

        } catch (e: Exception) {
            return Result.Failure(ApiException(cause = e))
        }
        return when {
            response.isSuccessful -> Result.Success(response.body())
            else -> {
                Result.Failure(if (response.errorBody() != null && errorClass != null) {
                    errorsMapper.createExceptionFromBody(response.errorBody(), errorClass)
                } else {
                    ApiException()
                })
            }
        }
    }
}
