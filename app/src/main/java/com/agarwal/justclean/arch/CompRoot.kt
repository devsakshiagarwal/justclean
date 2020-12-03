package com.agarwal.justclean.arch

import com.agarwal.justclean.Config
import com.agarwal.justclean.model.api.ApiHelper
import com.agarwal.justclean.model.api.ApiHelperImpl
import com.agarwal.justclean.model.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CompRoot {
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

  @Provides @Singleton
  fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}