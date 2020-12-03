package com.agarwal.justclean.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agarwal.justclean.model.dao.PostDao
import com.agarwal.justclean.model.schema.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun postDao(): PostDao
}