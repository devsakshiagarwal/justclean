package com.agarwal.justclean.di

import android.content.Context
import com.agarwal.justclean.Config
import com.agarwal.justclean.model.remote.ApiService
import com.agarwal.justclean.model.local.PostDao
import com.agarwal.justclean.model.local.AppDatabase
import com.agarwal.justclean.model.local.CommentDao
import com.agarwal.justclean.model.remote.RemoteDataSource
import com.agarwal.justclean.model.repository.CommentRepo
import com.agarwal.justclean.model.repository.PostRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
  @Provides fun provideBaseUrl() = Config.BASE_URL

  @Singleton @Provides fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Singleton @Provides fun provideRetrofit(okHttpClient: OkHttpClient,
    BASE_URL: String): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

  @Provides @Singleton fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

  @Singleton @Provides fun provideRemoteDataSource(apiService: ApiService) =
    RemoteDataSource(apiService)

  @Provides fun providePostDao(appDatabase: AppDatabase): PostDao {
    return appDatabase.postDao()
  }

  @Provides fun provideCommentDao(appDatabase: AppDatabase): CommentDao {
    return appDatabase.commentDao()
  }

  @Provides @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context) =
    AppDatabase.getDatabase(appContext)

  @Singleton @Provides
  fun providePostRepository(remoteDataSource: RemoteDataSource,
    localDataSourcePost: PostDao) =
    PostRepo(remoteDataSource, localDataSourcePost)

  @Singleton @Provides
  fun provideCommentRepository(remoteDataSource: RemoteDataSource,
    localDataSourceComment: CommentDao) =
    CommentRepo(remoteDataSource, localDataSourceComment)
}
