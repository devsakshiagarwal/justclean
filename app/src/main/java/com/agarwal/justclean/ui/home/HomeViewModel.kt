package com.agarwal.justclean.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.agarwal.justclean.model.repository.PostRepo

class HomeViewModel @ViewModelInject constructor(postRepo: PostRepo) : ViewModel() {
  val postList = postRepo.getPosts()
}
