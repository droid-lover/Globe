package com.nativemobilebits.globe.datasource.remote.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestQuery(
  val country:String
)
