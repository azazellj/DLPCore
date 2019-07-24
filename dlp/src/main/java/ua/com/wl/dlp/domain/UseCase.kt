package ua.com.wl.dlp.domain

import retrofit2.Response

import ua.com.wl.dlp.data.api.errors.ErrorsMapper
import ua.com.wl.dlp.domain.exeptions.ApiException
import ua.com.wl.dlp.domain.exeptions.ApiRuntimeException

open class UseCase(private val errorsMapper: ErrorsMapper) {

    protected suspend fun <T : Any> callApi(
        call: suspend () -> Response<T>,
        errorClass: Class<out ApiException>? = null): Result<T> = call.invoke().let {
        if (it.isSuccessful) {
            Result.Success(it.body())

        } else {
            Result.Failure(if (it.errorBody() != null && errorClass != null) {
                errorsMapper.createApiExceptionFromErrorBody(it.errorBody(), errorClass)

            } else {
                ApiRuntimeException()
            })
        }
    }
}