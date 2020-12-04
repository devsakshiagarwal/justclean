package com.agarwal.justclean.model.schema

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "post")
data class Post(
  @PrimaryKey
  @SerializedName("id") val id: Int = 0,
  @SerializedName("body") val body: String = "",
  @SerializedName("title") val title: String = "",
  @SerializedName("userId") val userId: Int = 0,
  var isFavorite: Boolean = false
) : Parcelable