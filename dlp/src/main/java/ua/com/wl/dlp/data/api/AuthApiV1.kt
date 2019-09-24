package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.requests.auth.SmsCodeRequest
import ua.com.wl.dlp.data.api.responses.BaseResponse

interface AuthApiV1 {

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v1/consumer/password-restore/")
    suspend fun restorePassword(@Body request: SmsCodeRequest): Response<BaseResponse>
}