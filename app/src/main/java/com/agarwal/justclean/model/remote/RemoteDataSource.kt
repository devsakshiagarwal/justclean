package com.agarwal.justclean.model.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) :
  BaseDataSource() {
  suspend fun getPosts() = getResult { apiService.getPosts() }

  suspend fun getComments(postId: Int) =
    getResult { apiService.getComments(postId) }
}