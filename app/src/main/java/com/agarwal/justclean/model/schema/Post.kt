package com.agarwal.justclean.model.schema

import com.google.gson.annotations.SerializedName

data class Post(@SerializedName("body") val body: String = "",
  @SerializedName("id") val id: Int = 0,
  @SerializedName("title") val title: String = "",
  @SerializedName("userId") val userId: Int = 0)