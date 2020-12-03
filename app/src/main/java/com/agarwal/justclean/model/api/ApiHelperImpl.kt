package com.agarwal.justclean.model.api

import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.model.schema.Post
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) :
  ApiHelper {
  override suspend fun getPosts(): Response<List<Post>> = apiService.getPosts()

  override suspend fun getComments(postId: String): Response<List<Comment>> =
    apiService.getComments(postId)
}