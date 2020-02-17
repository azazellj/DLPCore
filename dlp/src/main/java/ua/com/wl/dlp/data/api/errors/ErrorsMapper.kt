package ua.com.wl.dlp.data.api.errors

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

import com.google.gson.Gson

import okhttp3.ResponseBody

import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException
import ua.com.wl.dlp.domain.exeptions.api.ApiException

/**
 * @author Denis Makovskyi
 */

class ErrorsMapper constructor(private val gson: Gson) {

    fun createExceptionFromResponseBody(
        errorBody: ResponseBody,
        errorClass: KClass<out ApiException>
    ): Throwable {

        fun constructorArgs(argsSize: Int, message: String?): Array<Any?> {
            return Array(argsSize) { index ->
                if (index == 0) message else null
            }
        }

        val error = createErrorFromBody(errorBody)
        return if (error != null) {
            try {
                val constructor = requireNotNull(errorClass.primaryConstructor) {
                    "Primary constructor was not defined"
                }
                return when(val size = constructor.parameters.size) {
                    1 -> constructor.call(error.type)
                    in 2..Int.MAX_VALUE -> constructor.call(*constructorArgs(size, error.type))
                    else -> throw IllegalStateException("There is something wrong with constructor arguments")
                }

            } catch (e: Exception) {
                CoreRuntimeException(
                    createDetailMessage("could not instantiate class ${errorClass.java.name}"), e)
            }

        } else {
            CoreRuntimeException(
                createDetailMessage("could not unmarshall response error body"))
        }
    }

    private fun createErrorFromBody(body: ResponseBody): ApiError? =
        try {
            gson.fromJson(body.string(), ApiError::class.java)

        } catch (ignored: Exception) {
            null
        }

    private fun createDetailMessage(message: String): String = "${javaClass.name}: $message"
}