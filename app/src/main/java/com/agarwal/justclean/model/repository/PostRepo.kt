package com.agarwal.justclean.model.repository

import com.agarwal.justclean.model.api.ApiHelper
import javax.inject.Inject

class PostRepo @Inject constructor(private val apiHelper: ApiHelper) {
  suspend fun getPosts() = apiHelper.getPosts()

  suspend fun getComments(postId: String) = apiHelper.getComments(postId)
}