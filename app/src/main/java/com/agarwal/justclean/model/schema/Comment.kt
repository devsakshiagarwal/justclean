package com.agarwal.justclean.model.schema

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Comment(@SerializedName("body") val body: String = "",
  @SerializedName("email") val email: String = "",
  @PrimaryKey @SerializedName("id") val id: Int = 0,
  @SerializedName("name") val name: String = "",
  @SerializedName("postId") val postId: Int = 0)