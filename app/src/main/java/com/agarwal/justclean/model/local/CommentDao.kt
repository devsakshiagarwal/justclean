package com.agarwal.justclean.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.agarwal.justclean.model.schema.Comment

@Dao
interface CommentDao {
  @Query("SELECT * FROM comment WHERE postId = :id")
  fun getAllComments(id: Int): LiveData<List<Comment>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllComments(commentList: List<Comment>)
}