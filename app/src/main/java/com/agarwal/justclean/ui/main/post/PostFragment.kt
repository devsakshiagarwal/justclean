package com.agarwal.justclean.ui.main.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agarwal.justclean.R
import com.agarwal.justclean.arch.network.Status.ERROR
import com.agarwal.justclean.arch.network.Status.LOADING
import com.agarwal.justclean.arch.network.Status.SUCCESS
import com.agarwal.justclean.model.schema.Post
import com.agarwal.justclean.ui.main.MainViewModel
import com.agarwal.justclean.ui.main.comment.CommentFragment
import com.agarwal.justclean.ui.main.post.PostAdapter.OnPostClickListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_post.constraintLayout
import kotlinx.android.synthetic.main.fragment_post.rv_posts
import kotlinx.android.synthetic.main.layout_progress_bar.progress_bar

class PostFragment : Fragment(), OnPostClickListener {
  private lateinit var mainViewModel: MainViewModel

  companion object {
    @JvmStatic fun newInstance() = PostFragment()
  }

  override fun onCreateView(inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_post, container, false)
  }

  override fun onViewCreated(view: View,
    savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mainViewModel =
      ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    initViews()
    observeLiveData()
  }

  override fun onPostClick(post: Post) {
    CommentFragment.newInstance(post.id, post.body)
      .show(activity!!.supportFragmentManager, "comment_fragment")
  }

  private fun initViews() {
    mainViewModel.getPosts()
  }

  private fun observeLiveData() {
    mainViewModel.postsLiveData.observe(requireActivity(), Observer {
      when (it.status) {
        SUCCESS -> {
          setData(it.data!!)
        }
        LOADING -> {
          progress_bar.visibility = View.VISIBLE
        }
        ERROR -> {
          showError()
        }
      }
    })
  }

  private fun setData(postList: List<Post>) {
    progress_bar.visibility = View.GONE
    val postAdapter = PostAdapter(this)
    postAdapter.setPosts(postList)
    rv_posts.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = postAdapter
    }
  }

  private fun showError() {
    progress_bar.visibility = View.GONE
    Snackbar.make(constraintLayout, "Something went wrong",
      Snackbar.LENGTH_SHORT)
      .show()
  }
}