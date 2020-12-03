package com.agarwal.justclean.ui.main.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agarwal.justclean.R
import com.agarwal.justclean.arch.BaseDialogFragment
import com.agarwal.justclean.arch.network.Status
import com.agarwal.justclean.model.schema.Comment
import com.agarwal.justclean.ui.main.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.layout_progress_bar.progress_bar
import kotlinx.android.synthetic.main.layout_toolbar.*

class CommentFragment : BaseDialogFragment() {
  private lateinit var mainViewModel: MainViewModel
  private var postId = 0
  private var postName = ""

  companion object {
    @JvmStatic fun newInstance(postId: Int,
      postName: String) = CommentFragment().apply {
      arguments = Bundle().apply {
        putInt("post_id", postId)
        putString("post_name", postName)
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    container?.removeAllViews()
    return inflater.inflate(R.layout.fragment_comment, container, false)
  }

  override fun onViewCreated(view: View,
    savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mainViewModel =
      ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    arguments?.let {
      postId = it.getInt("post_id")
      postName = it.getString("post_name", "")
    }
    initViews()
    observeLiveData()
  }

  private fun initViews() {
    tool_bar.title = postName
    tool_bar.setNavigationIcon(R.drawable.ic_back)
    tool_bar.setNavigationOnClickListener { dismiss() }
    mainViewModel.getComments(postId)
  }

  private fun observeLiveData() {
    mainViewModel.commentsLiveData.observe(requireActivity(), Observer {
      when (it.status) {
        Status.SUCCESS -> {
          setData(it.data!!)
        }
        Status.LOADING -> {
          //progress_bar.visibility = View.VISIBLE
        }
        Status.ERROR -> {
          showError()
        }
      }
    })
  }

  private fun setData(commentList: List<Comment>) {
    //progress_bar.visibility = View.GONE
    val commentAdapter = CommentAdapter()
    commentAdapter.setComments(commentList)
    rv_comment.apply {
      layoutManager = LinearLayoutManager(requireActivity())
      adapter = commentAdapter
    }
  }

  private fun showError() {
    progress_bar.visibility = View.GONE
    Snackbar.make(constraintLayout, "Something went wrong",
      Snackbar.LENGTH_SHORT)
      .show()
  }
}