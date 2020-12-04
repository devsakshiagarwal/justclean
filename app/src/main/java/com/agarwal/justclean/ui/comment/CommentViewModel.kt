package com.agarwal.justclean.ui.comment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.agarwal.justclean.model.repository.CommentRepo
import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.utils.Response

class CommentViewModel @ViewModelInject constructor(private val commentRepo: CommentRepo) :
  ViewModel() {
  private val postId = MutableLiveData<Int>()
  private val comments = postId.switchMap { id ->
    commentRepo.getComments(id)
  }
  val commentList: LiveData<Response<List<Comment>>> = comments

  fun getComments(postId: Int) {
    this.postId.value = postId
  }
}
