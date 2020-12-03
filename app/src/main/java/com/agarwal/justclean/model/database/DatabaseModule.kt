package com.agarwal.justclean.model.database

import android.content.Context
import androidx.room.Room
import com.agarwal.justclean.model.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {
  @Provides fun providePostDao(appDatabase: AppDatabase): PostDao {
    return appDatabase.postDao()
  }

  @Provides @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return Room.databaseBuilder(appContext, AppDatabase::class.java,
      "JustClean")
      .build()
  }
}