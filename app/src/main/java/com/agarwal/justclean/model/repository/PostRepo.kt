package com.agarwal.justclean.model.repository

import com.agarwal.justclean.model.local.PostDao
import com.agarwal.justclean.model.remote.RemoteDataSource
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.utils.performGetOperation
import javax.inject.Inject

class PostRepo @Inject constructor(private val remoteDataSource: RemoteDataSource,
  private val localDataSourcePost: PostDao) {
  fun getPosts() =
    performGetOperation(databaseQuery = { localDataSourcePost.getAllPosts() },
      networkCall = { remoteDataSource.getPosts() },
      saveCallResult = { localDataSourcePost.insertAllPost(it) })

  fun getFavorites() = localDataSourcePost.getAllFavoritePosts()

  suspend fun updateFavorite(post: Post) = localDataSourcePost.updatePost(post)

}