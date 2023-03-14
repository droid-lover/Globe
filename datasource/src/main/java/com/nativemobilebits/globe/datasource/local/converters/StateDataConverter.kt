package com.nativemobilebits.globe.datasource.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nativemobilebits.globe.datasource.remote.response.DataStates

class StateDataConverter {
  @TypeConverter
  fun toStateData(json: String) =
    Gson().fromJson<DataStates>(
      json,
      object : TypeToken<DataStates>() {}.type
    )

  @TypeConverter
  fun fromStateData(item: DataStates?) = Gson().toJson(item)
}