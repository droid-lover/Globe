package com.nativemobilebits.globe.di

import com.nativemobilebits.globe.datasource.local.datasource.LocalDataSource
import com.nativemobilebits.globe.datasource.remote.datasource.CountryDataSource
import com.nativemobilebits.globe.navigation.CountryModuleRoute
import com.nativemobilebits.globe.navigation.CountryModuleRouteImpl
import com.nativemobilebits.globe.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CountryModule {

    @Singleton
    @Provides
    fun providesCountryModuleRoute(): CountryModuleRoute =
        CountryModuleRouteImpl()

    @Singleton
    @Provides
    fun provideCountryRepository(
        countryDataSource: CountryDataSource,
        localDataSource: LocalDataSource
    ): CountryRepository = CountryRepository(countryDataSource,localDataSource)


}
