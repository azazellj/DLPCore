package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.*

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.requests.auth.*
import ua.com.wl.dlp.data.api.responses.BaseResponse
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.auth.SignResponse
import ua.com.wl.dlp.data.api.responses.auth.TokenResponse
import ua.com.wl.dlp.data.api.responses.auth.CardsStatusResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse
import ua.com.wl.dlp.data.api.responses.models.auth.City

/**
 * @author Denis Makovskyi
 */

interface AuthApiV2 {

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v2/consumer/auth/token/verify/")
    suspend fun verification(@Body request: TokenRequest): Response<DataResponse<TokenResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v2/consumer/registration/check/")
    suspend fun authentication(@Body request: AuthenticationRequest): Response<DataResponse<AuthenticationResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v2/consumer/auth/token/refresh/")
    suspend fun refreshToken(@Body request: TokenRequest): Response<DataResponse<TokenResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v2/consumer/auth/token/")
    suspend fun signIn(@Body request: SignInRequest): Response<DataResponse<SignResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v2/consumer/registration/barcode/check/")
    suspend fun cardsStatus(@Body request: CardsStatusRequest): Response<DataResponse<CardsStatusResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v2/consumer/registration/")
    suspend fun signUp(@Body request: SignUpRequest): Response<DataResponse<SignResponse>>

    @PATCH("api/mobile/v2/consumer/device/")
    suspend fun deviceInfo(@Body request: DeviceInfoRequest): Response<BaseResponse>

    @POST("api/mobile/v2/consumer/auth/log-out/")
    suspend fun signOut(): Response<BaseResponse>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/mobile/v2/consumer/auth/code/")
    suspend fun requestSmsCode(@Body request: SmsCodeRequest): Response<BaseResponse>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @GET("api/mobile/v2/business/city/")
    suspend fun cities(): Response<DataResponse<PagedResponse<City>>>
}