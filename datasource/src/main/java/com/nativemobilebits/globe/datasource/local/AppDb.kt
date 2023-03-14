package com.nativemobilebits.globe.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nativemobilebits.globe.datasource.local.converters.CountriesDataDbModelConverter
import com.nativemobilebits.globe.datasource.local.converters.StateDataConverter
import com.nativemobilebits.globe.datasource.local.converters.StateDataDbModelConverter
import com.nativemobilebits.globe.datasource.local.dao.CountryDao
import com.nativemobilebits.globe.datasource.remote.response.CountriesResponse
import com.nativemobilebits.globe.datasource.remote.response.CountryDetailsResponse
import com.nativemobilebits.globe.datasource.util.TableConstants

@Database(
    entities = [
        CountriesResponse::class,
        CountryDetailsResponse::class], version = 1, exportSchema = false
)
@TypeConverters(
    CountriesDataDbModelConverter::class,
    StateDataDbModelConverter::class, StateDataConverter::class
)
abstract class AppDB : RoomDatabase() {

    abstract fun getCountryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: AppDB? = null

        fun getDB(context: Context): AppDB =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDB::class.java, TableConstants.APP_DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }

    fun destroyDB() {
        instance = null
    }
}