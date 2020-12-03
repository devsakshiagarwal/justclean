package com.agarwal.justclean.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.agarwal.justclean.model.schema.Post

@Dao
interface PostDao {
  @Query("SELECT * FROM post") fun getAllPosts(): List<Post>

  @Insert fun insertPost(post: Post)
}