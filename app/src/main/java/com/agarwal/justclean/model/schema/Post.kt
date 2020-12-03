package com.agarwal.justclean.model.schema

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post")
data class Post(
  @PrimaryKey
  @SerializedName("id") val id: Int = 0,
  @SerializedName("body") val body: String = "",
  @SerializedName("title") val title: String = "",
  @SerializedName("userId") val userId: Int = 0,
  val isFavorite: Boolean = false
)