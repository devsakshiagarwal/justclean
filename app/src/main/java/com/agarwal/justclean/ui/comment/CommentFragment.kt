package com.agarwal.justclean.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agarwal.justclean.R
import com.agarwal.justclean.model.local.SharedPref
import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.utils.InternetConnectionUtil
import com.agarwal.justclean.utils.Response
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.layout_progress_bar.progress_bar

@AndroidEntryPoint
class CommentFragment : Fragment() {
  private lateinit var commentViewModel: CommentViewModel
  private var post = Post()

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_comment, container, false)
  }

  override fun onViewCreated(view: View,
    savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
    arguments?.let {
      post = it.getParcelable("post")!!
    }
    initViews()
    observeLiveData()
  }

  private fun initViews() {
    if (!InternetConnectionUtil.isConnectedToInternet(
        requireActivity() as AppCompatActivity)) {
      showError(getString(R.string.err_no_internet))
    }
    handleFavorite()
    button_favorite.setOnClickListener {
      if (!InternetConnectionUtil.isConnectedToInternet(
          requireActivity() as AppCompatActivity)) {
        SharedPref(requireContext()).setUpdate(true)
      }
      post.isFavorite = !post.isFavorite
      commentViewModel.updatePost(post)
      handleFavorite()
    }
    commentViewModel.getComments(post.id)
  }

  private fun handleFavorite() {
    if (post.isFavorite) {
      button_favorite.text = getString(R.string.un_favorite)
    } else {
      button_favorite.text = getString(R.string.favorite)
    }
  }

  private fun observeLiveData() {
    commentViewModel.commentList.observe(viewLifecycleOwner, Observer {
      when (it.status) {
        Response.Status.SUCCESS -> {
          setData(it.data!!)
        }
        Response.Status.ERROR -> {
          showError(it.message!!)
        }
        Response.Status.LOADING -> progress_bar.visibility = View.VISIBLE
      }
    })
  }

  private fun setData(commentList: List<Comment>) {
    progress_bar.visibility = View.GONE
    val commentAdapter = CommentAdapter()
    commentAdapter.setComments(commentList)
    rv_comment.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = commentAdapter
    }
  }

  private fun showError(errorMessage: String) {
    progress_bar.visibility = View.GONE
    if (!InternetConnectionUtil.isConnectedToInternet(
        requireActivity() as AppCompatActivity)) {
      Snackbar.make(cl_comment, getString(R.string.err_no_internet),
        Snackbar.LENGTH_SHORT)
        .show()
    } else {
      Snackbar.make(cl_comment, errorMessage, Snackbar.LENGTH_SHORT)
        .show()
    }
  }
}