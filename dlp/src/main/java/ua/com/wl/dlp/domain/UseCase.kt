package ua.com.wl.dlp.domain

import android.util.Log
import retrofit2.Response

import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.domain.exeptions.ApiException
import ua.com.wl.dlp.domain.exeptions.ApiRuntimeException

/**
 * @author Denis Makovskyi
 */

open class UseCase(private val errorsMapper: ErrorsMapper) {

    protected suspend fun <T : Any> callApi(
        call: suspend () -> Response<T>,
        errorClass: Class<out ApiException>? = null): Result<T> =
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body())

            } else {
                Result.Failure(if (response.errorBody() != null && errorClass != null) {
                    errorsMapper.createApiExceptionFromErrorBody(response.errorBody(), errorClass)

                } else {
                    ApiRuntimeException()
                })
            }

        } catch (e: Exception) {
            Log.e("DLPCore", "Unexpected error when call api", e)
            Result.Failure(ApiRuntimeException())
        }
}
