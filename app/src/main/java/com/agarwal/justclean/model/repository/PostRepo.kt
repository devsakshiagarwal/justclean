package com.agarwal.justclean.model.repository

import com.agarwal.justclean.model.api.ApiHelper
import com.agarwal.justclean.model.dao.PostDao
import com.agarwal.justclean.model.schema.Post
import javax.inject.Inject

class PostRepo @Inject constructor(private val apiHelper: ApiHelper,
  private val postDao: PostDao) {
  suspend fun getPosts() = apiHelper.getPosts()

  fun addOrRemoveFavorite(post: Post) {
    post.isFavorite = !post.isFavorite
    postDao.insertPost(post)
  }

  suspend fun getComments(postId: Int) = apiHelper.getComments(postId)
}