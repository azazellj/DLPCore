package ua.com.wl.dlp.data.api.errors

import com.google.gson.Gson
import okhttp3.ResponseBody

import ua.com.wl.dlp.domain.exeptions.CoreException
import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException

/**
 * @author Denis Makovskyi
 */

class ErrorsMapper(private val gson: Gson) {

    fun createExceptionFromBody(errorBody: ResponseBody?, errorClass: Class<out CoreException>): Throwable =
        errorBody?.let { body ->
            createErrorFromBody(body)?.let { error ->
                try {
                    errorClass.getConstructor(String::class.java).newInstance(error.type)

                } catch (e: Exception) {
                    CoreRuntimeException("${javaClass.name}: could not instantiate class ${errorClass.name}")
                }

            } ?: CoreRuntimeException("${javaClass.name}: could not unmarshall response error body to structure")

        } ?: CoreRuntimeException("${javaClass.name}: response error body is empty")

    private fun createErrorFromBody(errorBody: ResponseBody): ApiError? =
        try {
            gson.fromJson(errorBody.string(), ApiError::class.java)

        } catch (e: Exception) {
            null
        }
}