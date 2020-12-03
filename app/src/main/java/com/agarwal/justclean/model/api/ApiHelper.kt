package com.agarwal.justclean.model.api

import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.model.schema.Post
import retrofit2.Response

interface ApiHelper {
  suspend fun getPosts(): Response<List<Post>>
  suspend fun getComments(postId: Int): Response<List<Comment>>
}