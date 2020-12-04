package com.agarwal.justclean.ui.comment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.agarwal.justclean.model.repository.CommentRepo
import com.agarwal.justclean.model.repository.PostRepo
import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.utils.Response
import kotlinx.coroutines.launch

class CommentViewModel @ViewModelInject constructor(private val commentRepo:
CommentRepo, private val postRepo: PostRepo) :
  ViewModel() {
  private val postId = MutableLiveData<Int>()
  private val comments = postId.switchMap { id ->
    commentRepo.getComments(id)
  }
  val commentList: LiveData<Response<List<Comment>>> = comments

  fun getComments(postId: Int) {
    this.postId.value = postId
  }

  fun updatePost(post: Post) =  viewModelScope.launch {
    postRepo.updateFavorite(post)
  }
}
