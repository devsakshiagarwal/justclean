package com.agarwal.justclean.model.repository

import com.agarwal.justclean.model.local.CommentDao
import com.agarwal.justclean.model.remote.RemoteDataSource
import com.agarwal.justclean.utils.performGetOperation
import javax.inject.Inject

class CommentRepo @Inject constructor(private val remoteDataSource: RemoteDataSource,
  private val localDataSourceComment: CommentDao) {
  fun getComments(postId: Int) = performGetOperation(databaseQuery = {
    localDataSourceComment.getAllComments(postId)
  }, networkCall = { remoteDataSource.getComments(postId) },
    saveCallResult = { localDataSourceComment.insertAllComments(it) })
}