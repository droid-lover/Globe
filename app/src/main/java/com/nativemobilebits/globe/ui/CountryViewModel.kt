package com.nativemobilebits.globe.ui

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nativemobilebits.globe.datasource.ResourceState
import com.nativemobilebits.globe.datasource.local.datasource.LocalDataSource
import com.nativemobilebits.globe.datasource.remote.entity.RequestQuery
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.remote.response.CountryDetailsResponse
import com.nativemobilebits.globe.datasource.util.CoreUtility
import com.nativemobilebits.globe.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val countryRepository: CountryRepository,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()
    var noInternet: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadingVisibility.value = View.GONE
        noInternet.value = false
    }


    /** API call to get countries list*/
    private val _countriesDataResponse: MutableLiveData<ResourceState<CountriesResponse>> =
        MutableLiveData()
    val countriesDataResponse: LiveData<ResourceState<CountriesResponse>> =
        _countriesDataResponse


    fun getCountries() {
        if (!CoreUtility.isInternetConnected(context)) {
            noInternet.value = true
            checkCountriesResponseFromLocal()
        } else {
            noInternet.value = false
            if (CoreUtility.checkNetworkAndToast(context)) {
                loadingVisibility.value = View.VISIBLE
                viewModelScope.launch {
                    countryRepository.getCountries().collect {
                        _countriesDataResponse.value = it
                    }
                }
            }
        }
    }


    /** API call to get countries states list*/
    private val _countryDetailsResponse: MutableLiveData<ResourceState<CountryDetailsResponse>> =
        MutableLiveData()
    val countryDetailsResponse: LiveData<ResourceState<CountryDetailsResponse>> =
        _countryDetailsResponse


    fun getCountryDetails(data: RequestQuery) {
        if (!CoreUtility.isInternetConnected(context)) {
            noInternet.value = true
        } else {
            noInternet.value = false
            if (CoreUtility.checkNetworkAndToast(context)) {
                loadingVisibility.value = View.VISIBLE
                viewModelScope.launch {
                    countryRepository.getCountryDetails(data).collect {
                        _countryDetailsResponse.value = it
                    }
                }
            }
        }
    }

    private fun checkCountriesResponseFromLocal() {
        _countriesDataResponse.value = ResourceState.success(localDataSource.getCountries())
    }

}