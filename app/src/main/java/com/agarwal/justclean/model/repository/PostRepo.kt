package com.agarwal.justclean.model.repository

import com.agarwal.justclean.model.local.CommentDao
import com.agarwal.justclean.model.local.PostDao
import com.agarwal.justclean.model.remote.RemoteDataSource
import com.agarwal.justclean.utils.performGetOperation
import javax.inject.Inject

class PostRepo @Inject constructor(private val remoteDataSource: RemoteDataSource,
  private val localDataSourcePost: PostDao,
  private val localDataSourceComment: CommentDao) {
  fun getPosts() =
    performGetOperation(databaseQuery = { localDataSourcePost.getAllPosts() },
      networkCall = { remoteDataSource.getPosts() },
      saveCallResult = { localDataSourcePost.insertAllPost(it) })

  fun getComments(postId: Int) = performGetOperation(databaseQuery = {
    localDataSourceComment.getAllComments(postId)
  }, networkCall = { remoteDataSource.getComments(postId) },
    saveCallResult = { localDataSourceComment.insertAllComments(it) })
}