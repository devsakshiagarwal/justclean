package com.agarwal.justclean.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.model.schema.Post

@Database(entities = [Post::class, Comment::class], version = 1, exportSchema =
false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun postDao(): PostDao
  abstract fun commentDao(): CommentDao

  companion object {
    @Volatile private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase =
      instance ?: synchronized(this) {
        instance ?: buildDatabase(context).also { instance = it }
      }

    private fun buildDatabase(appContext: Context) =
      Room.databaseBuilder(appContext, AppDatabase::class.java, "just_clean")
        .fallbackToDestructiveMigration()
        .build()
  }
}