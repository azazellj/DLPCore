package ua.com.wl.dlp.data.api

import retrofit2.Response
import retrofit2.http.GET

import ua.com.wl.dlp.data.api.responses.PaginationResponse
import ua.com.wl.dlp.data.api.responses.consumer.shop.CityShopsResponse

/**
 * @author Denis Makovskyi
 */

interface ConsumerApiV1 {

    @GET("api/v1/consumer/city-shops/")
    suspend fun getCityShops(): Response<PaginationResponse<CityShopsResponse>>
}