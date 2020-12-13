package ua.com.wl.dlp.data.api.errors

import com.squareup.moshi.Moshi
import retrofit2.HttpException
import retrofit2.Response
import ua.com.wl.dlp.domain.exeptions.CoreRuntimeException
import ua.com.wl.dlp.domain.exeptions.api.ApiException
import ua.com.wl.dlp.utils.fromJson

class ErrorsMapper(private val moshi: Moshi) {

    inline fun <T : ApiException> createExceptionFromResponse(
        response: Response<*>,
        errorMapper: (type: String?, cause: Throwable?) -> T
    ): Throwable {
        val cause = HttpException(response)
        val error = createErrorFromBody(response)
        return if (error != null) {
            errorMapper.invoke(error.type, cause)
        } else {
            CoreRuntimeException(createDetailMessage("could not unmarshall response error body"))
        }
    }

    fun createErrorFromBody(response: Response<*>): ApiError? {
        return try {
            moshi.fromJson<ApiError>(response.errorBody()?.string())
        } catch (ignored: Exception) {
            null
        }
    }

    fun createDetailMessage(message: String): String = "${javaClass.name}: $message"
}