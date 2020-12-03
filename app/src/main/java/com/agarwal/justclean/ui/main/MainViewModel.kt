package com.agarwal.justclean.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agarwal.justclean.arch.network.Response
import com.agarwal.justclean.model.repository.PostRepo
import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.model.schema.Post
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val postRepo: PostRepo) :
  ViewModel() {
  var postsLiveData = MutableLiveData<Response<List<Post>>>()
  var commentsLiveData = MutableLiveData<Response<List<Comment>>>()

  fun getPosts() = viewModelScope.launch {
    postsLiveData.value = Response.loading(null)
    postRepo.getPosts()
      .let {
        if (it.isSuccessful) {
          postsLiveData.value = Response.success(it.body())
        } else {
          postsLiveData.value = Response.error(it.errorBody()
            .toString(), null)
        }
      }
  }

  fun getComments(postId: String) = viewModelScope.launch {
    commentsLiveData.value = Response.loading(null)
    postRepo.getComments(postId)
      .let {
        if (it.isSuccessful) {
          commentsLiveData.value = Response.success(it.body())
        } else {
          commentsLiveData.value = Response.error(it.errorBody()
            .toString(), null)
        }
      }
  }
}
