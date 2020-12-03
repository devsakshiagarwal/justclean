package com.agarwal.justclean.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.agarwal.justclean.utils.Response
import com.agarwal.justclean.model.repository.PostRepo
import com.agarwal.justclean.model.schema.Comment

class MainViewModel @ViewModelInject constructor(private val postRepo: PostRepo) :
  ViewModel() {
  private val postId = MutableLiveData<Int>()
  private val comments = postId.switchMap { id ->
    postRepo.getComments(id)
  }
  val postList = postRepo.getPosts()
  val commentList: LiveData<Response<List<Comment>>> = comments

  fun getComments(postId: Int) {
    this.postId.value = postId
  }
}
