package ua.com.wl.dlp.data.api.errors

import com.google.gson.Gson
import okhttp3.ResponseBody

import ua.com.wl.dlp.domain.exeptions.ApiException
import ua.com.wl.dlp.domain.exeptions.ApiRuntimeException

/**
 * @author Denis Makovskyi
 */

class ErrorsMapper(private val gson: Gson) {

    fun createApiExceptionFromErrorBody(errorBody: ResponseBody?, errorClass: Class<out ApiException>): Throwable =
        errorBody?.let { body ->
            createErrorFromBody(body)?.let { error ->
                try {
                    errorClass.getConstructor(String::class.java).newInstance(error.type)

                } catch (e: Exception) {
                    ApiRuntimeException("${javaClass.name}: could not instantiate class ${errorClass.name}")
                }

            } ?: ApiRuntimeException("${javaClass.name}: could not unmarshall response error body to structure")

        } ?: ApiRuntimeException("${javaClass.name}: response error body is empty")

    private fun createErrorFromBody(errorBody: ResponseBody): ApiError? =
        try {
            gson.fromJson(errorBody.string(), ApiError::class.java)

        } catch (e: Exception) {
            null
        }
}