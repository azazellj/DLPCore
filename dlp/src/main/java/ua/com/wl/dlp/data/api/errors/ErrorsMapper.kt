package ua.com.wl.dlp.data.api.errors

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

import org.koin.ext.getFullName

import com.google.gson.Gson

import okhttp3.ResponseBody

import ua.com.wl.dlp.domain.exeptions.CoreException
import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException

/**
 * @author Denis Makovskyi
 */

class ErrorsMapper(private val gson: Gson) {

    fun createExceptionFromResponseBody(
        errorBody: ResponseBody,
        errorClass: KClass<out CoreException>
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
                    createDetailMessage("could not instantiate class ${errorClass.getFullName()}"), e)
            }

        } else {
            CoreRuntimeException(
                createDetailMessage("could not unmarshall response error body to structure"))
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