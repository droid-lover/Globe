package com.nativemobilebits.globe.datasource.di

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.nativemobilebits.globe.datasource.local.datasource.LocalDataSource
import com.nativemobilebits.globe.datasource.local.datasource.LocalDataSourceImpl
import com.nativemobilebits.globe.datasource.remote.api.CountryService
import com.nativemobilebits.globe.datasource.remote.datasource.CountryDataSource
import com.nativemobilebits.globe.datasource.remote.datasource.CountryDataSourceImpl
import com.nativemobilebits.globe.datasource.util.AppConstants
import com.nativemobilebits.globe.datasource.util.moshiadapter.PairAdapterFactoryMoshi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    private val TAG = "DataSourceModule"

    @Singleton
    @Provides
    fun providesCoreRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).add(PairAdapterFactoryMoshi())
                        .build()
                ).asLenient()
                    .withNullSerialization()
            )
            .client(httpClient.build())
            .build()
    }


    @Singleton
    @Provides
    fun provideCountryService(retrofit: Retrofit): CountryService {
        return retrofit.create(CountryService::class.java)
    }

    @Singleton
    @Provides
    fun providesCountryDataSource(
        countryService: CountryService
    ): CountryDataSource = CountryDataSourceImpl(countryService)


    @Singleton
    @Provides
    fun providesLocalDataSource(@ApplicationContext context: Context): LocalDataSource =
        LocalDataSourceImpl(context)

}


