package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

import ua.com.wl.dlp.core.Constants
import ua.com.wl.dlp.data.api.requests.auth.*
import ua.com.wl.dlp.data.api.responses.BaseResponse
import ua.com.wl.dlp.data.api.responses.DataResponse
import ua.com.wl.dlp.data.api.responses.PagedResponse
import ua.com.wl.dlp.data.api.responses.auth.TokenResponse
import ua.com.wl.dlp.data.api.responses.auth.AuthenticationResponse
import ua.com.wl.dlp.data.api.responses.auth.CardsStatusResponse
import ua.com.wl.dlp.data.api.responses.auth.SignResponse
import ua.com.wl.dlp.data.api.responses.models.auth.City

/**
 * @author Denis Makovskyi
 */

interface AuthApi {

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/auth/token/verify/")
    suspend fun verification(@Body request: TokenRequest): Response<DataResponse<TokenResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/auth/token/refresh/")
    suspend fun refreshToken(@Body request: TokenRequest): Response<DataResponse<TokenResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/registration/check/")
    suspend fun authentication(@Body request: AuthenticationRequest): Response<DataResponse<AuthenticationResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/auth/token/")
    suspend fun signIn(@Body request: SignInRequest): Response<DataResponse<SignResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/rs/os/cards/status/")
    suspend fun cardsStatus(@Body request: CardsStatusRequest): Response<DataResponse<CardsStatusResponse>>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/registration/")
    suspend fun signUp(@Body request: SignUpRequest): Response<DataResponse<SignResponse>>

    @POST("api/v3/consumer/auth/log-out/")
    suspend fun signOut(): Response<BaseResponse>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @POST("api/v3/consumer/auth/code/")
    suspend fun requestSmsCode(@Body request: SmsCodeRequest): Response<BaseResponse>

    @Headers("${Constants.HEADER_UNAUTHORIZED}: ${Constants.VALUE_PERMIT}")
    @GET("api/v3/business/city/")
    suspend fun cities(): Response<DataResponse<PagedResponse<City>>>
}