package com.nativemobilebits.globe.datasource.remote.api
import com.google.gson.JsonObject
import com.nativemobilebits.globe.datasource.remote.entity.RequestQuery
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.remote.response.CountryDetailsResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CountryService {

    @GET("countries/flag/images")
    suspend fun  getCountries(): Response<CountriesResponse>


    @POST("countries/states")
    suspend fun  getCountryDetails(@Body data:RequestQuery): Response<CountryDetailsResponse>



}