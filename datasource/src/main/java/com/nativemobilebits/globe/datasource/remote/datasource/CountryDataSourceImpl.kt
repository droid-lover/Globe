package com.nativemobilebits.globe.datasource.remote.datasource

import com.nativemobilebits.globe.datasource.remote.api.CountryService
import com.nativemobilebits.globe.datasource.remote.entity.RequestQuery
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.remote.response.CountryDetailsResponse
import retrofit2.Response
import javax.inject.Inject

class CountryDataSourceImpl @Inject constructor(
    private val countryService: CountryService
) : CountryDataSource {

    override suspend fun getCountries(): Response<CountriesResponse> {
        return countryService.getCountries()
    }

    override suspend fun getCountryDetails(data: RequestQuery): Response<CountryDetailsResponse> {
        return countryService.getCountryDetails(data)
    }


}