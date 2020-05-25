package ua.com.wl.dlp.data.api.errors

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

import com.google.gson.Gson

import retrofit2.Response
import retrofit2.HttpException

import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException
import ua.com.wl.dlp.domain.exeptions.api.ApiException

/**
 * @author Denis Makovskyi
 */

class ErrorsMapper(private val gson: Gson) {

    fun createExceptionFromResponse(
        response: Response<*>,
        errorClass: KClass<out ApiException>
    ): Throwable {

        fun constructorArgs(argsSize: Int, type: String?, cause: HttpException?): Array<Any?> {
            return Array(argsSize) { index ->
                when(index) {
                    1 -> type
                    2 -> cause
                    else -> null
                }
            }
        }

        val cause = HttpException(response)
        val error = createErrorFromBody(response)
        return if (error != null) {
            try {
                val constructor = requireNotNull(errorClass.primaryConstructor) {
                    "Primary constructor was not defined"
                }
                return when(val size = constructor.parameters.size) {
                    1 -> constructor.call(error.type)
                    2 -> constructor.call(error.type, cause)
                    in 3..Int.MAX_VALUE -> constructor.call(*constructorArgs(size, error.type, cause))
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

    private fun createErrorFromBody(response: Response<*>): ApiError? {
        return try {
            gson.fromJson(
                response.errorBody()?.string(),
                ApiError::class.java)

        } catch (ignored: Exception) {
            null
        }
    }

    private fun createDetailMessage(message: String): String = "${javaClass.name}: $message"
}