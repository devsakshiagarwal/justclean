package com.agarwal.justclean.model.remote

import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.utils.Urls
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
  @GET(Urls.URL_POST) suspend fun getPosts(): Response<List<Post>>

  @GET(Urls.URL_COMMENTS) suspend fun getComments(@Path(
    "post_id") postId: Int): Response<List<Comment>>
}