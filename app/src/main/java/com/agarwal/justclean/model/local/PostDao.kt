package com.agarwal.justclean.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.agarwal.justclean.model.schema.Post

@Dao
interface PostDao {
  @Query("SELECT * FROM post") fun getAllPosts(): LiveData<List<Post>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPost(post: Post)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllPost(postList: List<Post>)

  @Update suspend fun updatePost(post: Post)
}